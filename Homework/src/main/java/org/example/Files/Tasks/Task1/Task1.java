package org.example.Files.Tasks.Task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до першого файлу: ");
        String path1 = scanner.nextLine();
        System.out.print("Введіть шлях до другого файлу: ");
        String path2 = scanner.nextLine();

        try (BufferedReader reader1 = new BufferedReader(new FileReader(path1));
             BufferedReader reader2 = new BufferedReader(new FileReader(path2))) {

            String line1 = reader1.readLine();
            String line2 = reader2.readLine();
            int lineNumber = 1;
            boolean areEqual = true;

            while (line1 != null || line2 != null) {
                if (line1 == null || line2 == null || !line1.equals(line2)) {
                    areEqual = false;
                    System.out.println("Незбіг на рядку " + lineNumber + ":");
                    System.out.println("  Файл 1: " + (line1 != null ? line1 : "<кінець файлу>"));
                    System.out.println("  Файл 2: " + (line2 != null ? line2 : "<кінець файлу>"));
                }
                if (line1 != null) line1 = reader1.readLine();
                if (line2 != null) line2 = reader2.readLine();
                lineNumber++;
            }

            if (areEqual) {
                System.out.println("Файли повністю збігаються.");
            }
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }
    }
}