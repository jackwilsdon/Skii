package me.jackwilsdon.skii.core;

public abstract class Game {
    private boolean running;

    public boolean isRunning() {
        return running;
    }

    public void start() {
        if (!isRunning()) {
            running = true;

            onStart();

            while (isRunning()) {
                onFrame();
            }
        }
    }

    public void stop() {
        if (isRunning()) {
            running = false;

            onStop();
        }
    }

    public abstract void onStart();

    public abstract void onFrame();

    public abstract void onStop();
}
