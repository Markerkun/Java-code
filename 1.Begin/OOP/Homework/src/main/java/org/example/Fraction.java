package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction() {
        this.numerator = 1;
        this.denominator = 1;
    }

    public void setValues(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public void setValues(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("denominator be equal to zero!");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }
}