package me.jackwilsdon.skii;

import me.jackwilsdon.skii.util.NativeLoader;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (!NativeLoader.getInstance().load()) {
            throw new RuntimeException("Failed to load natives for platform!");
        }

        Game game = new SkiiGame();

        if (!game.onLoad()) {
            throw new RuntimeException("Failed to load game!");
        }

        game.start();
        game.onUnload();
    }
}
