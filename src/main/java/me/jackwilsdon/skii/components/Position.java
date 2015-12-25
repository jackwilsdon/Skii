package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;

public class Position implements Component {
    public float x, y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
