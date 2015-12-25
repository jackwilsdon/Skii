package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.parser.ComponentTemplate;

public class Position implements Component {
    public float x, y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position(ComponentTemplate template) {
        this(template.getField("x", 0f, Float.class), template.getField("y", 0f, Float.class));
    }
}
