package org.example;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Прівєт здаравяк!");
//
//        int a = 25;
//        System.out.println(a);
//
//        boolean b = true;
//        if (b)
//        {
//            System.out.println("b = "+b);
//        }
//
//        char c='?';
//        String str = "Вітя, гдє нямням";
//        System.out.println(str + c);
//
//
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Хто ти, воїн?");
//        System.out.println('>');
//        String string = scanner.nextLine();
//        System.out.println("Вітаю, "+string);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Розмір масиву: ");
        int size = scanner.nextInt();

        System.out.println("size = "+size);

        int massive[] = new int[size];
        Random rand = new Random();
        int min = 67;
        int max = 69;

        for (int i = 0; i<size;i++)
        {
            massive[i]=rand.nextInt(max-min+1)+min;
        }

        System.out.println();

        int count = 0;
        for (int item : massive)
        {
            System.out.printf("%d\t",item);
            if(item==68)
            {
                count++;
            }
        }
        System.out.println();
        System.out.println("В масиві кількість '68': "+ count);

//        int num = rand.nextInt(max-min+1)+min;
//        System.out.println("Num = "+num);
    }

}
