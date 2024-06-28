package com.ecom.ecommerce.interfaces;

public class Cat implements Animal{
    @Override
    public void eat() {
        System.out.println("cat eat");
    }

    @Override
    public void sleep() {
        System.out.println("cat sleep");
    }

    @Override
    public void walk() {
        System.out.println("cat walk");
    }

    @Override
    public void fly() {
        System.out.println("cat fly");
    }
}
