package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;
import org.lwjgl.input.Keyboard;

public class PlayerControlComponent implements Component {
    public boolean enabled;

    public int leftKey;
    public int rightKey;

    public PlayerControlComponent(boolean enabled, int leftKey, int rightKey) {
        this.enabled = enabled;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    public PlayerControlComponent(int leftKey, int rightKey) {
        this(true, leftKey, rightKey);
    }

    public PlayerControlComponent(boolean enabled) {
        this(enabled, Keyboard.KEY_A, Keyboard.KEY_D);
    }

    public PlayerControlComponent() {
        this(true);
    }
}
