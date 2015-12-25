package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;

public class Speed implements Component {
    public float speed;

    public Speed(float speed) {
        this.speed = speed;
    }
}
