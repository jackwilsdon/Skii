package me.jackwilsdon.skii.util;

public class Vector2f {
    public float x;
    public float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f() {
        this(0, 0);
    }

    public void add(Vector2f vector) {
        x += vector.x;
        y += vector.y;
    }

    public void sub(Vector2f vector) {
        x -= vector.x;
        y -= vector.y;
    }

    public static Vector2f random(float minX, float maxX, float minY, float maxY) {
        float xValue = (float) Math.random() * (maxX - minX) + minX;
        float yValue = (float) Math.random() * (maxY - minY) + minY;

        return new Vector2f(xValue, yValue);
    }

    public static Vector2f random(float maxX, float maxY) {
        return random(0, maxX, 0, maxY);
    }
}
