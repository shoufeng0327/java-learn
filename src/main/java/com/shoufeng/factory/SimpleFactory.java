package com.shoufeng.factory;

/**
 * 简单工厂
 */
public class SimpleFactory {
    public  static Car getInstance(CarEnum carEnum){
        Car car = null;
        switch (carEnum){
            case BMW: {
                car = new BMWCar();
                break;
            }
            case BENZ: {
                car = new BenzCar();
                break;
            }
        }
        return car;
    }

    public static void main(String[] args) {
        SimpleFactory.getInstance(CarEnum.BENZ).drive();
        SimpleFactory.getInstance(CarEnum.BMW).drive();
    }
}
