package org.example.Files.Tasks.Task4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до файлу для збереження: ");
        String path = scanner.nextLine();

        System.out.print("Введіть елементи масиву через пробіл: ");
        String[] parts = scanner.nextLine().trim().split("\\s+");
        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i]);
        }

        StringBuilder even = new StringBuilder();
        StringBuilder odd = new StringBuilder();
        StringBuilder reversed = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) even.append(array[i]).append(" ");
            else odd.append(array[i]).append(" ");

            reversed.append(array[array.length - 1 - i]).append(" ");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(joinArray(array));
            writer.newLine();
            writer.write(even.toString().trim());
            writer.newLine();
            writer.write(odd.toString().trim());
            writer.newLine();
            writer.write(reversed.toString().trim());

            System.out.println("Дані успішно збережено у файл!");
        } catch (IOException e) {
            System.err.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    private static String joinArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) sb.append(num).append(" ");
        return sb.toString().trim();
    }
}