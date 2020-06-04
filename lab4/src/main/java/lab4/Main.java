package lab4;

import lab4.DB.DBClass;
import lab4.DB.Utils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("LAB 4");
        System.out.println("___________________________________");
        System.out.println("Программа формирует базу данных.");
        DBClass db = new DBClass();
        Scanner inScanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter how many rows will be created (int N): ");

            if (inScanner.hasNextLine()) {
                Scanner line = new Scanner(inScanner.nextLine());
                if (line.hasNextInt()) {
                    Utils.initialize(db, line.nextInt());
                    break;
                } else {
                    System.out.println("Incorrect integer!");
                }
            }
        }

        System.out.println("Available commands:");
        for (String key : Utils.handlers.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        String cmd;
        while (inScanner.hasNextLine()) {
            Utils.execute(db, inScanner.nextLine());
        }

        inScanner.close();
        db.disconnect();
    }
}
