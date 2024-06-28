package com.ecom.ecommerce.llodOne;

public class DemoStudentMainClass {

    public static void main(String[] args){
        Student s1 = new Student();

        s1.id=7;
        s1.name="John";
        s1.school="KLU";

        s1.coursePaused();;
        s1.recentGrad();
    }
}
