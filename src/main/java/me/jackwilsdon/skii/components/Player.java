package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;

public class Player implements Component {
    public int upKey;
    public int downKey;

    public Player(int upKey, int downKey) {
        this.upKey = upKey;
        this.downKey = downKey;
    }
}
