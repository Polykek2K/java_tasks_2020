package lab5;

import lab5.DB.DBClass;
import lab5.DB.Utils;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("LAB 5");
        System.out.println("___________________________________");
        System.out.println("Графический интерфейс для лабораторной работы №4.");
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
        inScanner.close();

        JFrame frame = new JFrame("Lab5");
        Grid grid = new Grid();
        grid.db = db;
        grid.updateList();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(frame);
        frame.setContentPane(grid.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

