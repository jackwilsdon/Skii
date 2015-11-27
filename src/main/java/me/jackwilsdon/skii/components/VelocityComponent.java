package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.skii.util.Vector2f;

public class VelocityComponent implements Component {
    public Vector2f velocity;

    public VelocityComponent(Vector2f position) {
        this.velocity = position;
    }

    public VelocityComponent(float x, float y) {
        this(new Vector2f(x, y));
    }

    public VelocityComponent() {
        this(0, 0);
    }
}