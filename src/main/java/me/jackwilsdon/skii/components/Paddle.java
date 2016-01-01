package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;

public class Paddle implements Component {
    public boolean moveUp;
    public boolean moveDown;

    public float width;
    public float height;

    public int direction;

    public Paddle(float width, float height, int direction) {
        this.width = width;
        this.height = height;
        this.direction = direction;
    }
}
