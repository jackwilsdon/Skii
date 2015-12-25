package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.parser.ComponentTemplate;

public class Paddle implements Component {
    public boolean moveUp;
    public boolean moveDown;

    public float width;
    public float height;

    public Paddle(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public Paddle(ComponentTemplate template) {
        this(template.getField("width", Float.class), template.getField("height", Float.class));
    }
}
