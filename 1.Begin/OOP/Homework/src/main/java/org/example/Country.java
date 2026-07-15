package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Country {
    private String name;
    private String continent;
    private long population;
    private String phoneCode;
    private String capital;

    public Country() {
        this.name = "Unknown";
        this.continent = "Unknown";
        this.population = 0L;
        this.phoneCode = "+000";
        this.capital = "Unknown";
    }

    public void initCountry(String name, String continent, long population, String phoneCode, String capital) {
        this.name = name;
        this.continent = continent;
        this.population = population;
        this.phoneCode = phoneCode;
        this.capital = capital;
    }
}