package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;

public class Ball implements Component {
    public float width;
    public float height;

    public Ball(float width, float height) {
        this.width = width;
        this.height = height;
    }
}
