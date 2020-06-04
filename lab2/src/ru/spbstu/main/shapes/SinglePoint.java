package ru.spbstu.main.shapes;

public class SinglePoint implements Point {
    public SinglePoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private final float x;
    private final float y;

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}
