package ru.spbstu.main.shapes;

/**
 * Представление о треугольнике.
 * <p>
 * Треуго́льник (в евклидовом пространстве) — геометрическая
 * фигура, образованная тремя отрезками, которые соединяют
 * три точки, не лежащие на одной прямой. Указанные три
 * точки называются вершинами треугольника, а отрезки —
 * сторонами треугольника. Часть плоскости, ограниченная
 * сторонами, называется внутренностью треугольника: нередко
 * треугольник рассматривается вместе со своей внутренностью
 * (например, для определения понятия площади).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%A2%D1%80%D0%B5%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Треугольник</a>
 */
public class Triangle implements Polygon {

    /*
     * TODO: Реализовать класс 'Triangle'
     * 1. Используйте наследование.
     * 2. Реализуйте все абстрактные методы.
     */

    public Triangle(SinglePoint a, SinglePoint b, SinglePoint c) {
        double eps = 0.00001;
        if (distance(a, b) + distance(a, c) - distance(b, c) < eps) {
            throw new IllegalArgumentException("Degenerate triangle is not allowed!");
        }
        points = new SinglePoint[]{a, b, c};
    }

    private final SinglePoint[] points;
    private float rotation;

    @Override
    public float getArea() {
        float A = distance(this.points[0], this.points[1]);
        float B = distance(this.points[1], this.points[2]);
        float C = distance(this.points[2], this.points[0]);
        float p = this.getPerimeter() / 2;

        return (float) Math.sqrt(p * (p - A) * (p - B) * (p - C));
    }

    @Override
    public float getX() {
        float sum = 0;
        for (SinglePoint point : points) {
            sum += point.getX();
        }
        return sum / points.length;
    }

    @Override
    public float getY() {
        float sum = 0;
        for (SinglePoint point : points) {
            sum += point.getY();
        }
        return sum / points.length;
    }

    @Override
    public float getPerimeter() {
        return distance(this.points[0], this.points[1]) +
                distance(this.points[1], this.points[2]) +
                distance(this.points[2], this.points[0]);
    }

    @Override
    public void rotate(float angle) {
        float tmp = this.rotation + angle;
        this.rotation = tmp > 360 ? tmp - 360 : tmp;
    }

    @Override
    public void setRotation(float angle) {
        this.rotation = angle > 0 ? angle : 360 + angle;
    }

    @Override
    public float getRotation() {
        return this.rotation;
    }

    private float distance(Point a, Point b) {
        return (float) Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }
}
