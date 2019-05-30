package com.shoufeng.facade;

public class Light implements Product{
    @Override
    public void close() {
        System.out.println("close light");
    }

    @Override
    public void open() {
        System.out.println("open light");
    }
}
