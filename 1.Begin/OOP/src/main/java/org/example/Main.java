package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("YES! I'M AM!");
//        Animal cat = new Animal();
//        cat.setName("Redhat");
//        cat.setAge(6);
//        System.out.println(cat);
//
//        Animal mary = new Animal("Mary", 4);
//        System.out.println(mary);

        Cat cat = new Cat();
        System.out.println(cat);

        Dog dog = new Dog();
        System.out.println(dog);

        ArrayList<Animal> list = new ArrayList<Animal>();
        list.add(cat);
        list.add(new Cat("Elyzavetta", 2, false));
        list.add(dog);

    }
}