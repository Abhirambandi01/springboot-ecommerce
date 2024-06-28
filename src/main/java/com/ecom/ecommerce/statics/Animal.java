package com.ecom.ecommerce.statics;

public class Animal {
    public static int val = 10;
//     public int val = 20;

    public static String staticMethod(){

        System.out.println("accessing static value "+ val);

        return "Hello World";
    }
}
