package me.jackwilsdon.skii.core;

import me.jackwilsdon.skii.util.NativeLoader;

public class Main {
    public static void main(String[] args) {
        NativeLoader.getInstance().load();
        SkiiGame.getInstance().start();
    }
}
