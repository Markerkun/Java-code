package org.example.Files.Tasks.Task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        String path = "arrays.txt"; // Вкажіть потрібний шлях до файлу
        List<int[]> arraysList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                int[] array = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    array[i] = Integer.parseInt(parts[i]);
                }
                arraysList.add(array);
            }
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
            return;
        }

        int totalSum = 0;
        for (int index = 0; index < arraysList.size(); index++) {
            int[] arr = arraysList.get(index);
            int min = arr[0];
            int max = arr[0];
            int sum = 0;

            for (int num : arr) {
                if (num < min) min = num;
                if (num > max) max = num;
                sum += num;
            }
            totalSum += sum;

            System.out.println("Масив " + (index + 1) + ": " + Arrays.toString(arr));
            System.out.println("  Мін: " + min + ", Макс: " + max + ", Сума: " + sum);
        }
        System.out.println("Загальна сума всіх масивів: " + totalSum);
    }
}