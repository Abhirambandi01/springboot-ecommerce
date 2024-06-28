package com.ecom.ecommerce.passBy;

public class PassByValue {
    public static void main(String[] args) {
        int val=10;
        System.out.println("initial value is "+ val);
        doSomething(val);
        System.out.println("after initial value is "+ val);
    }
    public static void doSomething(int val) {
        val = 20;
        System.out.println("intermediate value is "+val);
    }
}
