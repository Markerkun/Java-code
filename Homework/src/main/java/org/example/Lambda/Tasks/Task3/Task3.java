package org.example.Lambda.Tasks.Task3;

public class Task3 {

    @FunctionalInterface
    interface QuadFunction<T> {
        T apply(T a, T b, T c, T d);
    }

    public static void main(String[] args) {
        System.out.println("=== ЗАВДАННЯ 3 ===");

        // Максимум із чотирьох
        QuadFunction<Integer> maxOfFour = (a, b, c, d) -> Math.max(Math.max(a, b), Math.max(c, d));

        // Мінімум із чотирьох
        QuadFunction<Double> minOfFour = (a, b, c, d) -> Math.min(Math.min(a, b), Math.min(c, d));

        System.out.println("Максимум з (5, 12, 3, 9): " + maxOfFour.apply(5, 12, 3, 9));
        System.out.println("Мінімум з (4.5, 1.2, 9.8, -2.3): " + minOfFour.apply(4.5, 1.2, 9.8, -2.3));
    }
}