package com.shoufeng.adapter;

public class IphonePowerAdapter implements PowerAdapter{
    @Override
    public int changePower(int power) {
        return power - 200;
    }
}
