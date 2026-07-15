package org.example;

public class Main {
    public static void main(String[] args) {
        // Create instances of the simple classes in this package
        Book book = new Book();
        Car car = new Car();
        Fraction fraction = new Fraction();
        Human human = new Human();
        City city = new City();
        Country country = new Country();

        System.out.println("Book: " + book);
        System.out.println("Car: " + car);
        System.out.println("Fraction: " + fraction);
        System.out.println("Human: " + human);
        System.out.println("City: " + city);
        System.out.println("Country: " + country);
    }
}