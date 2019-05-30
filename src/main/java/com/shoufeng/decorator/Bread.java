package com.shoufeng.decorator;

public class Bread implements Food{

    private Food baseFood;

    public Bread(Food baseFood) {
        this.baseFood = baseFood;
    }

    @Override
    public void make() {
        if (baseFood != null) baseFood.make();
        System.out.println("Bread is made");
    }
}
