package me.jackwilsdon.skii.components;

public class InterpolatedPosition extends Position {
    public float startX;
    public float startY;

    public float endX;
    public float endY;

    public InterpolatedPosition(float x, float y) {
        super(x, y);

        this.startX = x;
        this.startY = y;

        this.endX = x;
        this.endY = y;
    }
}
