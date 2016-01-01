package me.jackwilsdon.skii;

import me.jackwilsdon.skii.util.TickListener;
import me.jackwilsdon.skii.util.Ticker;

public abstract class TickGame extends Game implements TickListener {
    private Ticker ticker;

    public TickGame(int tickRate) {
        this.ticker = new Ticker(tickRate);
        this.ticker.addTickListener(this);
    }

    public Ticker getTicker() {
        return ticker;
    }

    @Override
    public void onFrame() {
        this.ticker.update();
    }
}
