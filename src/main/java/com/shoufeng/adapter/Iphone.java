package com.shoufeng.adapter;

public class Iphone implements Phone{

    private PowerAdapter powerAdapter;

    private int power = 220;

    public Iphone(PowerAdapter powerAdapter) {
        this.powerAdapter = powerAdapter;
    }

    @Override
    public int charge() {
        if (powerAdapter != null){
            power = powerAdapter.changePower(power);
        }
        System.out.println("iphone 开始充电：" + power);
        return power;
    }

    public static void main(String[] args) {
        Iphone iphone = new Iphone(new IphonePowerAdapter());
        iphone.charge();
    }
}
