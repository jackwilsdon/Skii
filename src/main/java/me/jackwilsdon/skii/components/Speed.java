package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.parser.ComponentTemplate;

public class Speed implements Component {
    public float speed;

    public Speed(float speed) {
        this.speed = speed;
    }

    public Speed(ComponentTemplate template) {
        this(template.getField("speed", Float.class));
    }
}
