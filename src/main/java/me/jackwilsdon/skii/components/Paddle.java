package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;

public class Paddle implements Component {
    public boolean moveUp;
    public boolean moveDown;

    public float width;
    public float height;

    public Paddle(float width, float height) {
        this.width = width;
        this.height = height;
    }
}
