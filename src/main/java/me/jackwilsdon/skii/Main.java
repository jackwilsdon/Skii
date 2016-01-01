package me.jackwilsdon.skii;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.skii.components.Player;
import me.jackwilsdon.skii.util.NativeLoader;

public class Main {

    public static void main(String[] args) {
        NativeLoader.getInstance().load();
        new SkiiGame().start();
    }
}
