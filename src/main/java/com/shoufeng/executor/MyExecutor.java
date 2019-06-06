package com.shoufeng.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyExecutor implements Executor{

    private TaskQueue taskQueue = new TaskQueue();	/*任务队列*/
    private final int maxThreads;	/*最大线程数*/
    private final int maxIdleThreads; /*最大空闲线程数*/
    private AtomicInteger idleThreads = new AtomicInteger(0);/*空闲线程数*/
    private boolean shutdown = false;	/*关闭标识*/
    private final Map<Long, Thread> threadMap = new ConcurrentHashMap<Long, Thread>();	/*线程容器*/

    public MyExecutor() {
        this(5, 1);
    }

    public MyExecutor(int maxThreads, int maxIdleThreads) {
        maxIdleThreads = maxIdleThreads < 1 ? 1 : maxIdleThreads;
        this.maxThreads = maxThreads;
        this.maxIdleThreads = maxIdleThreads;
    }

    @Override
    public void execute(Runnable task) {
        if(shutdown) {
            return;
        }
        //判断是否有空闲线程
        if(idleThreads.get()  <= 0 && threadMap.size() < maxThreads) {
            //创建工作线程
            createWorker();
        }
        //提交任务
        taskQueue.add(task);
    }

    /**
     * 平缓关闭线程池
     */
    public void shutdown() {
        shutdown = true;
        taskQueue.shutdown();
    }

    /**
     * 立即关闭线程池
     */
    public void shutdownNow() {
        shutdown();
        //清空任务队列
        taskQueue.clear();
        //尝试中断线程
        interruptAll();
    }

    private synchronized void createWorker() {
        //双重判断是否有空闲线程
        if(idleThreads.get()  > 0 || threadMap.size() >= maxThreads) {
            return;
        }

        Thread worker = new Thread(new Runnable() {

            @Override
            public void run() {
                //空闲线程数加加
                idleThreads.getAndIncrement();
                while(true) {
                    //获取任务
                    Runnable task = taskQueue.pop();
                    //空闲线程数减减
                    idleThreads.getAndDecrement();
                    if(null == task)
                        break;
                    try {
                        //执行任务
                        task.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(shutdown) {
                        break;
                    }
                    int temp = idleThreads.get();
                    //利用CAS方法将空闲线程数加加
                    while(temp < maxIdleThreads && !idleThreads.compareAndSet(temp, temp + 1)) {
                        temp = idleThreads.get();
                    }
                    //判断线程是否可以进入空闲状态
                    if(temp < maxIdleThreads && !shutdown) {
                        continue;
                    }
                    break;
                }
                //将线程从线程池中移除
                threadMap.remove(Thread.currentThread().getId());
            }
        });
        //将工作加入到容器中
        threadMap.put(worker.getId(), worker);
        //开启工作线程
        worker.start();
    }

    private void interruptAll() {
        for(Entry<Long, Thread> entry : threadMap.entrySet()) {
            entry.getValue().interrupt();
        }
    }

}

class TaskQueue{
    private final List<Runnable> taskList = new ArrayList<Runnable>();
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition empty = lock.newCondition();
    private boolean shutdown = false;

    public void add(Runnable task) {
        lock.lock();
        try {
            if(shutdown)
                return;
            taskList.add(task);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Runnable pop() {
        lock.lock();
        try {
            while (taskList.isEmpty()) {
                if(shutdown) {
                    return null;
                }
                notEmpty.await(1, TimeUnit.SECONDS);
            }
            Runnable task = taskList.remove(0);
            if(taskList.isEmpty())
                empty.signalAll();
            return task;
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void clear() {
        lock.lock();
        try {
            taskList.clear();
        }finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        shutdown = true;
    }

}
