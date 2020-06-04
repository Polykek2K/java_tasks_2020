package ru.spbstu.main;
import ru.spbstu.main.shapes.*;

public class Main {
    public static float biggestArea(Shape[] shapes) {
        if (shapes == null) {
            throw new NullPointerException("Empty array!");
        }
        float biggest = 0;
        for (Shape shape : shapes) {
            System.out.println(shape.getClass().getSimpleName() + " area: " + shape.getArea());
            if (shape.getArea() > biggest) {
                biggest = shape.getArea();
            }
        }
        return biggest;
    }

    public static void main(String[] args) {
        /*
         * TODO: Выполнить действия над массивом 'shapes'
         *
         * 1. Проинициализировать переменную 'shapes' массивом
         *    содержащим 10 произвольных фигур. Массив должен
         *    содержать экземпляры классов Circle, Rectangle
         *    и Triangle.
         *
         * 2. Найти в массиве 'shapes' фигуру с максимальной
         *    площадью. Для поиска фигуры необходимо создать
         *    статический метод в текущем классе (Main).
         */
        System.out.println("LAB 2");
        System.out.println("___________________________________");
        System.out.println("Программа выводит площади заданных фигур и выводит максимальную.");
        Shape[] shapes = new Shape[]{
                new Circle(new SinglePoint(1, 1), 0.5f),
                new Rectangle(new SinglePoint(1, 2), 1, 2),
                new Triangle(new SinglePoint(2, 2), new SinglePoint(2, 4), new SinglePoint(-1, -8)),
                new Circle(new SinglePoint(1, 1), 1),
                new Rectangle(new SinglePoint(7, 2), 4, 2),
                new Triangle(new SinglePoint(0, 2), new SinglePoint(2, 3), new SinglePoint(0, 0)),
                new Circle(new SinglePoint(-1, 1), 1.5f),
                new Rectangle(new SinglePoint(-1, 2), 3, 3),
                new Triangle(new SinglePoint(1, 0), new SinglePoint(3, 3), new SinglePoint(-1, -4)),
                new Circle(new SinglePoint(3, 4), 0.25f)
        };

        System.out.println("The biggest shape size is: " + biggestArea(shapes));
    }
}
