package org.example.Lambda.Tasks.Task1;

import java.time.LocalDate;
import java.time.Year;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;

public class Task1 {

    @FunctionalInterface
    interface DateDiffCalculator {
        long calculate(LocalDate start, LocalDate end);
    }

    public static void main(String[] args) {
        System.out.println("=== ЗАВДАННЯ 1 ===");

        // 1. Перевірка чи є рік високосним
        Predicate<Integer> isLeapYear = year -> Year.isLeap(year);
        System.out.println("2024 рік високосний: " + isLeapYear.test(2024));

        // 2. Підрахунок кількості днів між двома датами
        DateDiffCalculator daysBetween = (start, end) -> ChronoUnit.DAYS.between(start, end);
        LocalDate date1 = LocalDate.of(2024, 1, 1);
        LocalDate date2 = LocalDate.of(2024, 12, 31);
        System.out.println("Днів між датами: " + daysBetween.calculate(date1, date2));

        // 3. Підрахунок кількості повних тижнів (неділь) між двома датами
        DateDiffCalculator weeksBetween = (start, end) -> ChronoUnit.WEEKS.between(start, end);
        System.out.println("Повних тижнів між датами: " + weeksBetween.calculate(date1, date2));

        // 4. Підрахунок дня тижня по отриманій даті
        Function<LocalDate, String> getDayOfWeekName = date -> {
            DayOfWeek day = date.getDayOfWeek();
            return day.getDisplayName(TextStyle.FULL, new Locale("uk", "UA"));
        };
        LocalDate apollo11 = LocalDate.of(1969, 7, 20);
        System.out.println("20 липня 1969 року: " + getDayOfWeekName.apply(apollo11));
    }
}