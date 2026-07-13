package org.example;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
////        System.out.println("Прівєт здаравяк!");
////
////        int a = 25;
////        System.out.println(a);
////
////        boolean b = true;
////        if (b)
////        {
////            System.out.println("b = "+b);
////        }
////
////        char c='?';
////        String str = "Вітя, гдє нямням";
////        System.out.println(str + c);
////
////
////
////        Scanner scanner = new Scanner(System.in);
////        System.out.println("Хто ти, воїн?");
////        System.out.println('>');
////        String string = scanner.nextLine();
////        System.out.println("Вітаю, "+string);
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Розмір масиву: ");
//        int size = scanner.nextInt();
//
//        System.out.println("size = "+size);
//
//        int massive[] = new int[size];
//        Random rand = new Random();
//        int min = 67;
//        int max = 69;
//
//        for (int i = 0; i<size;i++)
//        {
//            massive[i]=rand.nextInt(max-min+1)+min;
//        }
//
//        System.out.println();
//
//        int count = 0;
//        for (int item : massive)
//        {
//            System.out.printf("%d\t",item);
//            if(item==68)
//            {
//                count++;
//            }
//        }
//        System.out.println();
//        System.out.println("В масиві кількість '68': "+ count);
//
////        int num = rand.nextInt(max-min+1)+min;
////        System.out.println("Num = "+num);

        public class Task1 {
            public static void main(String[] args) {
                System.out.println("“Your time is limited,");
                System.out.println("so don’t waste it");
                System.out.println("living someone else’s life”");
                System.out.println("Steve Jobs");
            }
        }

        public class Task2 {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введіть число: ");
                double value = scanner.nextDouble();
                System.out.print("Введіть відсоток: ");
                double percent = scanner.nextDouble();

                double result = (value * percent) / 100;
                System.out.println(percent + " відсотків від " + value + " — це " + result);
            }
        }

        public class Task3 {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введіть першу цифру: ");
                int d1 = scanner.nextInt();
                System.out.print("Введіть другу цифру: ");
                int d2 = scanner.nextInt();
                System.out.print("Введіть третю цифру: ");
                int d3 = scanner.nextInt();

                int result = d1 * 100 + d2 * 10 + d3;
                System.out.println("Сформоване число: " + result);
            }
        }

        import java.util.Scanner;

        public class Task4 {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введіть шестизначне число: ");
                int number = scanner.nextInt();

                if (number < 100000 || number > 999999) {
                    System.out.println("Помилка: Число не є шестизначним!");
                    return;
                }

                // Розбиваємо на окремі цифри
                int d1 = number / 100000;
                int d2 = (number / 10000) % 10;
                int d3 = (number / 1000) % 10;
                int d4 = (number / 100) % 10;
                int d5 = (number / 10) % 10;
                int d6 = number % 10;

                // Міняємо місцями: 1-а з 6-ю, 2-а з 5-ю. 3-я і 4-а залишаються
                int result = d6 * 100000 + d5 * 10000 + d3 * 1000 + d4 * 100 + d2 * 10 + d1;
                System.out.println("Результат: " + result);
            }
        }

        import java.util.Scanner;

        public class Task5 {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введіть номер місяця (1-12): ");
                int month = scanner.nextInt();

                if (month < 1 || month > 12) {
                    System.out.println("Помилка: Некоректний номер місяця!");
                } else if (month == 12 || month == 1 || month == 2) {
                    System.out.println("Winter");
                } else if (month >= 3 && month <= 5) {
                    System.out.println("Spring");
                } else if (month >= 6 && month <= 8) {
                    System.out.println("Summer");
                } else {
                    System.out.println("Autumn");
                }
            }
        }

        import java.util.Scanner;

        public class Task6 {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введіть кількість метрів: ");
                double meters = scanner.nextDouble();

                System.out.println("Оберіть одиницю для переводу:");
                System.out.println("1 - Милі\n2 - Дюйми\n3 - Ярди");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> System.out.println(meters + " м = " + (meters * 0.000621371) + " миль");
                    case 2 -> System.out.println(meters + " м = " + (meters * 39.3701) + " дюймів");
                    case 3 -> System.out.println(meters + " м = " + (meters * 1.09361) + " ярдів");
                    default -> System.out.println("Некоректний вибір!");
                }
            }
        }

        public class Task7 {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введіть перше число: ");
                int start = scanner.nextInt();
                System.out.print("Введіть друге число: ");
                int end = scanner.nextInt();

                // Нормалізація
                if (start > end) {
                    int temp = start;
                    start = end;
                    end = temp;
                }

                System.out.println("Непарні числа в діапазоні від " + start + " до " + end + ":");
                for (int i = start; i <= end; i++) {
                    if (i % 2 != 0) {
                        System.out.print(i + " ");
                    }
                }
                System.out.println();
            }
        }

        public class Task8 {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введіть початок діапазону: ");
                int start = scanner.nextInt();
                System.out.print("Введіть кінець діапазону: ");
                int end = scanner.nextInt();

                // Нормалізація про всяк випадок
                if (start > end) {
                    int temp = start;
                    start = end;
                    end = temp;
                }

                for (int i = start; i <= end; i++) {
                    for (int j = 1; j <= 10; j++) {
                        System.out.print(i + " * " + j + " = " + (i * j) + "\t");
                    }
                    System.out.println(); // Перехід на новий рядок для наступного числа
                }
            }
        }

        public class Task9 {
            public static void main(String[] args) {
                int[] array = new int[20];
                Random random = new Random();

                // Заповнення випадковими числами від -50 до 50
                System.out.print("Масив: ");
                for (int i = 0; i < array.length; i++) {
                    array[i] = random.nextInt(101) - 50;
                    System.out.print(array[i] + " ");
                }
                System.out.println();

                int min = array[0];
                int max = array[0];
                int negatives = 0, positives = 0, zeros = 0;

                for (int num : array) {
                    if (num < min) min = num;
                    if (num > max) max = num;

                    if (num < 0) negatives++;
                    else if (num > 0) positives++;
                    else zeros++;
                }

                System.out.println("Мінімум: " + min);
                System.out.println("Максимум: " + max);
                System.out.println("Кількість від'ємних: " + negatives);
                System.out.println("Кількість додатних: " + positives);
                System.out.println("Кількість нулів: " + zeros);
            }
        }

        import java.util.Arrays;
import java.util.Random;

        public class Task10 {
            public static void main(String[] args) {
                int[] original = new int[20];
                Random random = new Random();
                for (int i = 0; i < original.length; i++) {
                    original[i] = random.nextInt(101) - 50;
                }
                System.out.println("Оригінальний масив: " + Arrays.toString(original));

                int evenCount = 0, oddCount = 0, negCount = 0, posCount = 0;
                for (int num : original) {
                    if (num % 2 == 0) evenCount++;
                    else oddCount++;

                    if (num < 0) negCount++;
                    else if (num > 0) posCount++;
                }

                // Створюємо та заповнюємо масиви
                int[] evens = new int[evenCount];
                int[] odds = new int[oddCount];
                int[] negatives = new int[negCount];
                int[] positives = new int[posCount];

                int idxEven = 0, idxOdd = 0, idxNeg = 0, idxPos = 0;
                for (int num : original) {
                    if (num % 2 == 0) evens[idxEven++] = num;
                    else odds[idxOdd++] = num;

                    if (num < 0) negatives[idxNeg++] = num;
                    else if (num > 0) positives[idxPos++] = num;
                }

                System.out.println("Лише парні: " + Arrays.toString(evens));
                System.out.println("Лише непарні: " + Arrays.toString(odds));
                System.out.println("Лише від'ємні: " + Arrays.toString(negatives));
                System.out.println("Лише додатні: " + Arrays.toString(positives));
            }
        }

        public class Task11 {
            public static void drawLine(int length, String direction, char symbol) {
                for (int i = 0; i < length; i++) {
                    if (direction.equalsIgnoreCase("horizontal")) {
                        System.out.print(symbol);
                    } else if (direction.equalsIgnoreCase("vertical")) {
                        System.out.println(symbol);
                    }
                }
                if (direction.equalsIgnoreCase("horizontal")) {
                    System.out.println(); // переніс рядка після завершення горизонтальної лінії
                }
            }

            public static void main(String[] args) {
                drawLine(10, "horizontal", '*');
                System.out.println();
                drawLine(5, "vertical", '#');
            }
        }

        import java.util.Arrays;

        public class Task12 {
            public static void sortArray(int[] array, boolean ascending) {
                for (int i = 0; i < array.length - 1; i++) {
                    for (int j = 0; j < array.length - i - 1; j++) {
                        if (ascending) {
                            if (array[j] > array[j + 1]) {
                                int temp = array[j];
                                array[j] = array[j + 1];
                                array[j + 1] = temp;
                            }
                        } else {
                            if (array[j] < array[j + 1]) {
                                int temp = array[j];
                                array[j] = array[j + 1];
                                array[j + 1] = temp;
                            }
                        }
                    }
                }
            }

            public static void main(String[] args) {
                int[] arr = {5, 2, 9, 1, 3, 8};

                System.out.println("Оригінальний: " + Arrays.toString(arr));

                sortArray(arr, true);
                System.out.println("По зростанню:  " + Arrays.toString(arr));

                sortArray(arr, false);
                System.out.println("По спаданню:   " + Arrays.toString(arr));
            }
        }
    }

}
