package ru.spbstu.main.shapes;

/**
 * Представление об окружности.
 * <p>
 * Окру́жность — замкнутая плоская кривая, которая состоит из
 * всех точек на плоскости, равноудалённых от заданной точки.
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9E%D0%BA%D1%80%D1%83%D0%B6%D0%BD%D0%BE%D1%81%D1%82%D1%8C">Окружность</a>
 */
public class Circle implements Ellipse {

    /*
     * TODO: Реализовать класс 'Circle'
     * 1. Используйте наследование.
     * 2. Реализуйте все абстрактные методы.
     */
    public Circle(SinglePoint center, float radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Negative radius is not allowed!");
        }
        this.center = center;
        this.radius = radius;
    }

    public Circle(float x, float y, float radius) {
        this.center = new SinglePoint(x, y);
        this.radius = radius;
    }

    private final SinglePoint center;
    private final float radius;

    @Override
    public float getX() {
        return center.getX();
    }

    @Override
    public float getY() {
        return center.getY();
    }

    @Override
    public float getArea() {
        return (float) (Math.PI * Math.pow(radius, 2));
    }

    @Override
    public float getLength() {
        return (float) (2 * Math.PI * radius);
    }
}
