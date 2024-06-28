package com.ecom.ecommerce.lldInheritance;

public class Instructor extends User{
    public String batchName;
    public int avgRating;
    public void schedulingBatch(){
        System.out.println("Instructor scheduling batch ");
    }
}
