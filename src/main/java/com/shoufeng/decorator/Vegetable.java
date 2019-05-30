package com.shoufeng.decorator;

public class Vegetable implements Food{

    private Food baseFood;

    public Vegetable(Food baseFood) {
        this.baseFood = baseFood;
    }

    @Override
    public void make() {
        if (baseFood != null) baseFood.make();
        System.out.println("Vegetable is made");
    }

    public static void main(String[] args) {
        new Bread(new Vegetable(null)).make();
    }
}
