package lab3;

import static java.lang.Thread.sleep;

public class Main {
    public static synchronized void main(String[] args) throws InterruptedException {
        System.out.println("LAB 3");
        System.out.println("___________________________________");
        System.out.println("Программа моделирует процесс сдачи лабораторных работ и распараллеливает.");
        RobotQueue queue = new RobotQueue();
        sleep(10000);
        queue.stop();
    }
}

