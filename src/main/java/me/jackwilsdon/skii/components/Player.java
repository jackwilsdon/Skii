package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.parser.ComponentTemplate;

public class Player implements Component {
    public int upKey;
    public int downKey;

    public Player(int upKey, int downKey) {
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public Player(ComponentTemplate template) {
        this(template.getField("upKey", Integer.class), template.getField("downKey", Integer.class));
    }
}
