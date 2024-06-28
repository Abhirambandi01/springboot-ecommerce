package com.ecom.ecommerce.passBy;

public class passByReference {
    public static void main(String[] args) {
        Person person = new Person("John", 10);
        modifiedPerson(person);
        System.out.println("final result "+ person.getName() + " "+ person.getAge());
    }
    public static void modifiedPerson(Person person) {
        System.out.println("before modification "+ person.getName() + " "+ person.getAge());
        person.setName("Abhi");
        person.setAge(22);
        System.out.println("after modification "+ person.getName() + " "+ person.getAge());
    }
}

class Person{
    String name;
    int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
