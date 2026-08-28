package org.example.Lambda.Tasks.Task2;

import org.example.Lambda.Tasks.Fraction;

public class Task2 {

    @FunctionalInterface
    interface FractionOperation {
        Fraction apply(Fraction f1, Fraction f2);
    }

    public static void main(String[] args) {
        System.out.println("=== ЗАВДАННЯ 2 ===");

        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(1, 3);

        // Сума двох дробів
        FractionOperation add = (a, b) -> new Fraction(
                a.getNumerator() * b.getDenominator() + b.getNumerator() * a.getDenominator(),
                a.getDenominator() * b.getDenominator()
        );

        // Різниця двох дробів
        FractionOperation subtract = (a, b) -> new Fraction(
                a.getNumerator() * b.getDenominator() - b.getNumerator() * a.getDenominator(),
                a.getDenominator() * b.getDenominator()
        );

        // Множення двох дробів
        FractionOperation multiply = (a, b) -> new Fraction(
                a.getNumerator() * b.getNumerator(),
                a.getDenominator() * b.getDenominator()
        );

        // Ділення двох дробів
        FractionOperation divide = (a, b) -> new Fraction(
                a.getNumerator() * b.getDenominator(),
                a.getDenominator() * b.getNumerator()
        );

        System.out.println(f1 + " + " + f2 + " = " + add.apply(f1, f2));
        System.out.println(f1 + " - " + f2 + " = " + subtract.apply(f1, f2));
        System.out.println(f1 + " * " + f2 + " = " + multiply.apply(f1, f2));
        System.out.println(f1 + " / " + f2 + " = " + divide.apply(f1, f2));
    }
}