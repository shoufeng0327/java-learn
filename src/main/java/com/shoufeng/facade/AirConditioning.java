package com.shoufeng.facade;

public class AirConditioning implements Product{
    @Override
    public void close() {
        System.out.println("AirConditioning close");
    }

    @Override
    public void open() {
        System.out.println("AirConditioning open");
    }
}
