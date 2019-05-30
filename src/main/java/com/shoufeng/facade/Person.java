package com.shoufeng.facade;

/**
 * 门面模式
 */
public class Person {
    public static void main(String[] args) {
        Light light = new Light();
        AirConditioning airConditioning = new AirConditioning();
//        light.close();
//        airConditioning.close();
//        System.out.println("离家");
        leave(light,airConditioning);
    }

    private static void leave(Light light, AirConditioning airConditioning){
        light.close();
        airConditioning.close();
        System.out.println("离家");
    }
}
