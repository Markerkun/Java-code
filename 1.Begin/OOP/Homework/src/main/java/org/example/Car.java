package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    private String Model;
    private String manufacturer;
    private int manufactureYear;
    private double engineVolume;

    public Car() {
        this.Model = "Unknown Model";
        this.manufacturer = "Unknown Manufacturer";
        this.manufactureYear = 2000;
        this.engineVolume = 0.0;
    }

    public void inputDetails(String name, String manufacturer, int manufactureYear, double engineVolume) {
        this.Model = name;
        this.manufacturer = manufacturer;
        this.manufactureYear = manufactureYear;
        this.engineVolume = engineVolume;
    }
}