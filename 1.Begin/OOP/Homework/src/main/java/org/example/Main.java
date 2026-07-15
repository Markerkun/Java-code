package org.example;

public class Main {
    public static void main(String[] args) {
        // Create instances of the simple classes in this package
        Book book = new Book();
        Car car = new Car();
        Fraction fraction = new Fraction();
        Human human = new Human();

        System.out.println("Book: " + book);
        System.out.println("Car: " + car);
        System.out.println("Fraction: " + fraction);
        System.out.println("Human: " + human);

        // Note: City and Country files in this repo currently define a Human class
        // (they contain class Human with Lombok annotations). If you intend to have
        // City and Country classes, either rename the classes in those files to
        // City and Country, or update them accordingly. Then you can uncomment
        // the lines below to create City/Country objects:
        // City city = new City();
        // Country country = new Country();
        // System.out.println("City: " + city);
        // System.out.println("Country: " + country);
    }
}
