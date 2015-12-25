package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;

public class Velocity implements Component {
    public float x, y;

    public Velocity(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
