package com.ecom.ecommerce.abstracts;

import com.ecom.ecommerce.interfaces.Animal;

public class Client {
    public static void main(String[] args) {
        Animal a = (Animal) new Tiger();

    }
}
