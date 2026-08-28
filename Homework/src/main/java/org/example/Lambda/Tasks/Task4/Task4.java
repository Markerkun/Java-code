package org.example.Lambda.Tasks.Task4;

import java.util.function.IntPredicate;

public class Task4 {

    public static void main(String[] args) {
        System.out.println("=== ЗАВДАННЯ 4 ===");

        int[] numbers = {10, -5, 3, 8, -2, 15, 0, 7};

        // 1. Перевірка на рівність конкретному числу (наприклад, 8)
        int target = 8;
        int sumEquals = sumByCondition(numbers, x -> x == target);
        System.out.println("Сума елементів, рівних " + target + ": " + sumEquals);

        // 2. Число не знаходиться в діапазоні від A до B (наприклад, не від 0 до 10)
        int minRange = 0, maxRange = 10;
        int sumNotInRange = sumByCondition(numbers, x -> x < minRange || x > maxRange);
        System.out.println("Сума елементів поза діапазоном [" + minRange + ", " + maxRange + "]: " + sumNotInRange);

        // 3. Перевірка на додатне число
        int sumPositive = sumByCondition(numbers, x -> x > 0);
        System.out.println("Сума додатних елементів: " + sumPositive);

        // 4. Перевірка на від'ємне число
        int sumNegative = sumByCondition(numbers, x -> x < 0);
        System.out.println("Сума від'ємних елементів: " + sumNegative);
    }

    // Метод, який приймає масив та лямбду-умову (IntPredicate)
    public static int sumByCondition(int[] array, IntPredicate condition) {
        int sum = 0;
        for (int value : array) {
            if (condition.test(value)) {
                sum += value;
            }
        }
        return sum;
    }
}