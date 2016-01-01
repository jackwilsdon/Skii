package me.jackwilsdon.skii.properties;

import me.jackwilsdon.ecs.core.Property;
import me.jackwilsdon.skii.util.Ticker;

public class TickerProperty implements Property {
    public Ticker ticker;

    public TickerProperty(Ticker ticker) {
        this.ticker = ticker;
    }
}
