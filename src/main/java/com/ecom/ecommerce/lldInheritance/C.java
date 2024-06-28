package com.ecom.ecommerce.lldInheritance;

public class C extends B{
    public int val;
    public C(int val){
        this.val=val;
        System.out.println("C Constructor with value "+ val);
    }
//    public C(){
//        System.out.println("Constructor of C");
//    }
}
