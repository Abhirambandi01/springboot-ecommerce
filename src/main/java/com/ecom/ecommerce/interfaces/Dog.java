package com.ecom.ecommerce.interfaces;

public class Dog implements Animal{
    @Override
    public void eat() {
        System.out.println(" Dog eat");
    }

    @Override
    public void sleep() {
        System.out.println(" Dog sleep");
    }

    @Override
    public void walk() {
        System.out.println(" Dog walk");
    }

    @Override
    public void fly() {
        System.out.println(" Dog fly");
    }
}
