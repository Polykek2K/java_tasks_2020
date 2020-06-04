package lab1;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Main {
    static boolean isPrime(int n) {
        for (int i = 2; i <= sqrt(n); i++)
            if (n % i == 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("LAB 1");
        System.out.println("___________________________________");
        System.out.println("Программа выводит простые числа из заданного промежутка.");
        System.out.println("Введите два целых числа через пробел:");
        int a;
        int b;
        while (true) {
            try {
                a = Integer.parseInt(scn.next());
                b = Integer.parseInt(scn.next());
                scn.close();
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Введите целое число!");
            }
        }
        if (a > b) {
            for (int i = b; i != a; i++) {
                if (isPrime(i) && i > 1)
                    System.out.print(i + " ");
            }
        } else if (a < b) {
            for (int i = a; i != b; i++) {
                if (isPrime(i) && i > 1)
                    System.out.print(i + " ");
            }
        } else System.out.println("Числа равны, промежутка нет");
    }
}