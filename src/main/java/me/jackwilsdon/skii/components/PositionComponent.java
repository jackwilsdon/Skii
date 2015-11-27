package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.skii.util.Vector2f;

public class PositionComponent implements Component {
    public Vector2f position;

    public PositionComponent(Vector2f position) {
        this.position = position;
    }

    public PositionComponent(float x, float y) {
        this(new Vector2f(x, y));
    }

    public PositionComponent() {
        this(0, 0);
    }
}