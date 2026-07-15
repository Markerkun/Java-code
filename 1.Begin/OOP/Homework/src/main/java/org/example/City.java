package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class City {
    private String name;
    private int age;
    private long population;
    private double density;
    private double area;
    private String region;


    public City() {
        this.name = "Unknown";
        this.age = 0;
        this.population = 1;
        this.density = 1.0;
        this.area = 1.0;
        this.region = "Unknown";
    }

    public void inputData(String name, int age, long population, double density, double area, String region) {
        this.name = name;
        this.age = age;
        this.population = population;
        this.density = density;
        this.area = area;
        this.region = region;
    }
}