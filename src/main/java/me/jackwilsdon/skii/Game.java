package me.jackwilsdon.skii;

import me.jackwilsdon.skii.util.TickListener;
import me.jackwilsdon.skii.util.Ticker;

public abstract class Game implements TickListener {
    private Ticker ticker;
    private float deltaTime;
    private boolean running;

    public Game(int tickRate) {
        ticker = new Ticker(tickRate);
        ticker.addTickListener(this);
    }

    public final float getDeltaTime() {
        return deltaTime;
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        if (isRunning()) {
            return;
        }

        running = true;
        ticker.reset();

        while (running) {
            ticker.update();
            onFrame();
        }
    }

    public void stop() {
        running = false;
    }

    @Override
    public final void onTick(float deltaTime) {
        this.deltaTime = deltaTime;
        onTick();
    }

    public abstract boolean onLoad();
    public abstract void onTick();
    public abstract void onFrame();
    public abstract void onUnload();
}
