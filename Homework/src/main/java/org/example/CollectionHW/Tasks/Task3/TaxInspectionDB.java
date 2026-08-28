package org.example.CollectionHW.Tasks.Task3;

import java.util.*;

class Fine {
    String type;
    double amount;
    String city;

    public Fine(String type, double amount, String city) {
        this.type = type;
        this.amount = amount;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("[%s | Сума: %.2f грн | Місто: %s]", type, amount, city);
    }
}

class PersonTaxRecord {
    String idCode;
    String name;
    List<Fine> fines = new ArrayList<>();

    public PersonTaxRecord(String idCode, String name) {
        this.idCode = idCode;
        this.name = name;
    }
}

public class TaxInspectionDB {
    private static Map<String, PersonTaxRecord> database = new HashMap<>();

    public static void main(String[] args) {
        // Додавання людей та штрафів
        addPerson("1234567890", "Іванов Іван");
        addFine("1234567890", new Fine("Перевищення швидкості", 340, "Київ"));
        addFine("1234567890", new Fine("Паркування у неналежному місці", 680, "Львів"));

        addPerson("0987654321", "Петров Петро");
        addFine("0987654321", new Fine("Перевищення швидкості", 340, "Київ"));

        // Роздрукування даних
        printFullDatabase();
        printByCode("1234567890");
        printByFineType("Перевищення швидкості");
        printByCity("Львів");
    }

    public static void addPerson(String idCode, String name) {
        database.putIfAbsent(idCode, new PersonTaxRecord(idCode, name));
    }

    public static void addFine(String idCode, Fine fine) {
        if (database.containsKey(idCode)) {
            database.get(idCode).fines.add(fine);
        }
    }

    public static void printFullDatabase() {
        System.out.println("\n=== ПОВНИЙ ДРУК БАЗИ ДАНИХ ===");
        database.values().forEach(p -> System.out.println("ІПН: " + p.idCode + " | ПІБ: " + p.name + " | Штрафи: " + p.fines));
    }

    public static void printByCode(String idCode) {
        System.out.println("\n=== ДАНІ ПО КОДУ " + idCode + " ===");
        PersonTaxRecord p = database.get(idCode);
        if (p != null) System.out.println("ПІБ: " + p.name + " | Штрафи: " + p.fines);
    }

    public static void printByFineType(String type) {
        System.out.println("\n=== ШТРАФИ З ТИПОМ: " + type + " ===");
        database.values().forEach(p -> p.fines.stream()
                .filter(f -> f.type.equalsIgnoreCase(type))
                .forEach(f -> System.out.println(p.name + " (" + p.idCode + "): " + f)));
    }

    public static void printByCity(String city) {
        System.out.println("\n=== ШТРАФИ У МІСТІ: " + city + " ===");
        database.values().forEach(p -> p.fines.stream()
                .filter(f -> f.city.equalsIgnoreCase(city))
                .forEach(f -> System.out.println(p.name + " (" + p.idCode + "): " + f)));
    }
}