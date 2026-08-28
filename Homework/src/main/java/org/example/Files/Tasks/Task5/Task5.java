package org.example.Files.Tasks.Task5;

import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private String lastName;
    private String firstName;
    private int age;

    public Employee(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public int getAge() { return age; }

    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return lastName + " " + firstName + ", вік: " + age;
    }
}

public class Task5 {
    private static List<Employee> employees = new ArrayList<>();
    private static String currentFilePath;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до файлу з даними співробітників: ");
        currentFilePath = scanner.nextLine();

        loadData(currentFilePath);

        while (true) {
            System.out.println("\n--- Меню Корпорація ---");
            System.out.println("1. Додати співробітника");
            System.out.println("2. Редагувати співробітника");
            System.out.println("3. Видалити співробітника");
            System.out.println("4. Пошук за прізвищем");
            System.out.println("5. Вивід інформації (фільтр за віком / першою буквою)");
            System.out.println("6. Зберегти інформацію у файл");
            System.out.println("7. Вихід");
            System.out.print("Оберіть дію: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addEmployee(scanner);
                case "2" -> editEmployee(scanner);
                case "3" -> deleteEmployee(scanner);
                case "4" -> searchByLastName(scanner);
                case "5" -> filterAndPrint(scanner);
                case "6" -> saveFoundInfo(scanner);
                case "7" -> {
                    saveData(currentFilePath, employees);
                    System.out.println("Дані збережено. До побачення!");
                    return;
                }
                default -> System.out.println("Невірний вибір.");
            }
        }
    }

    private static void addEmployee(Scanner sc) {
        System.out.print("Прізвище: ");
        String lName = sc.nextLine();
        System.out.print("Ім'я: ");
        String fName = sc.nextLine();
        System.out.print("Вік: ");
        int age = Integer.parseInt(sc.nextLine());
        employees.add(new Employee(lName, fName, age));
    }

    private static void editEmployee(Scanner sc) {
        System.out.print("Введіть прізвище співробітника для редагування: ");
        String name = sc.nextLine();
        for (Employee e : employees) {
            if (e.getLastName().equalsIgnoreCase(name)) {
                System.out.print("Нове прізвище: ");
                e.setLastName(sc.nextLine());
                System.out.print("Нове ім'я: ");
                e.setFirstName(sc.nextLine());
                System.out.print("Новий вік: ");
                e.setAge(Integer.parseInt(sc.nextLine()));
                System.out.println("Дані оновлено.");
                return;
            }
        }
        System.out.println("Співробітника не знайдено.");
    }

    private static void deleteEmployee(Scanner sc) {
        System.out.print("Введіть прізвище для видалення: ");
        String name = sc.nextLine();
        employees.removeIf(e -> e.getLastName().equalsIgnoreCase(name));
        System.out.println("Співробітника видалено, якщо він був у списку.");
    }

    private static void searchByLastName(Scanner sc) {
        System.out.print("Введіть прізвище: ");
        String name = sc.nextLine();
        List<Employee> found = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getLastName().equalsIgnoreCase(name)) {
                found.add(e);
            }
        }
        found.forEach(System.out::println);
    }

    private static void filterAndPrint(Scanner sc) {
        System.out.println("1. Покажи за віком\n2. Покажи за першою буквою прізвища");
        String opt = sc.nextLine();
        if (opt.equals("1")) {
            System.out.print("Введіть вік: ");
            int age = Integer.parseInt(sc.nextLine());
            employees.stream().filter(e -> e.getAge() == age).forEach(System.out::println);
        } else if (opt.equals("2")) {
            System.out.print("Введіть букву: ");
            String letter = sc.nextLine().toLowerCase();
            employees.stream().filter(e -> e.getLastName().toLowerCase().startsWith(letter)).forEach(System.out::println);
        }
    }

    private static void saveFoundInfo(Scanner sc) {
        System.out.print("Введіть шлях до файлу для збереження вигрузки: ");
        String path = sc.nextLine();
        System.out.print("Введіть пошукове прізвище для збереження: ");
        String name = sc.nextLine();
        List<Employee> found = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getLastName().equalsIgnoreCase(name)) found.add(e);
        }
        saveData(path, found);
    }

    @SuppressWarnings("unchecked")
    private static void loadData(String path) {
        File f = new File(path);
        if (!f.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Дані успішно завантажено.");
        } catch (Exception e) {
            System.out.println("Створено новий список (не вдалося зчитати файл).");
        }
    }

    private static void saveData(String path, List<Employee> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(list);
            System.out.println("Дані записано.");
        } catch (IOException e) {
            System.err.println("Помилка збереження: " + e.getMessage());
        }
    }
}