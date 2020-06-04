package ru.spbstu.main.shapes;

/**
 * Представление о прямоугольнике.
 * <p>
 * Прямоугольник — четырехугольник, у которого все углы
 * прямые (равны 90 градусам).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D1%8F%D0%BC%D0%BE%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Прямоугольник</a>
 */
public class Rectangle implements Polygon {

    /*
     * TODO: Реализовать класс 'Rectangle'
     * 1. Используйте наследование.
     * 2. Реализуйте все абстрактные методы.
     */

    public Rectangle(SinglePoint center, float width, float height) {
        if ((width <= 0) || (height <= 0)) {
            throw new IllegalArgumentException("Negative width and height are not allowed!");
        }
        this.center = center;
        this.width = width;
        this.height = height;
    }

    private final SinglePoint center;
    private final float width;
    private final float height;
    private float rotation;

    @Override
    public float getArea() {
        return this.width * this.height;
    }

    @Override
    public float getX() {
        return center.getX();
    }

    @Override
    public float getY() {
        return center.getY();
    }

    @Override
    public void setRotation(float angle) {
        this.rotation = angle > 0 ? angle : 360 + angle;
    }

    @Override
    public float getRotation() {
        return this.rotation;
    }

    @Override
    public void rotate(float angle) {
        float tmp = this.rotation + angle;
        this.rotation = tmp > 360 ? tmp - 360 : tmp;
    }

    @Override
    public float getPerimeter() {
        return 2 * (width + height);
    }
}
