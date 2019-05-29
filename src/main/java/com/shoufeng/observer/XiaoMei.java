package com.shoufeng.observer;

import java.util.ArrayList;
import java.util.List;

public class XiaoMei {

  List<Person> personList = new ArrayList<>();

  public void addPerson(Person person) {
    personList.add(person);
  }

  //遍历list，把自己的通知发送给所有暗恋自己的人
  public void notifyPerson() {
    for (Person person : personList) {
      person.getMessage("今天家里就我一个人，你们过来吧，谁先过来谁就能得到我!");
    }
  }

  public static void main(String[] args) {
    XiaoMei xiaoMei = new XiaoMei();
    xiaoMei.addPerson(new XiaoLi());
    xiaoMei.addPerson(new LaoWang());
    xiaoMei.notifyPerson();
  }

}
