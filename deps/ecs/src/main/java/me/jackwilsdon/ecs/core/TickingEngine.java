package me.jackwilsdon.ecs.core;

import me.jackwilsdon.ecs.core.subsystem.SubSystem;
import me.jackwilsdon.ecs.util.FrameListener;
import me.jackwilsdon.ecs.util.TickListener;
import me.jackwilsdon.ecs.util.Ticker;

public class TickingEngine extends Engine implements TickListener, FrameListener {
    private Ticker ticker;

    public TickingEngine(int tickRate) {
        ticker = new Ticker(tickRate);

        ticker.addTickListener(this);
    }

    @Override
    public void onTick(float deltaTime) {
        for (SubSystem subSystem : getSubSystemManager().getSubSystems()) {
            subSystem.onTick(deltaTime);
        }
    }

    @Override
    public void onFrame() {
        ticker.update();

        for (SubSystem subSystem : getSubSystemManager().getSubSystems()) {
            subSystem.onFrame();
        }
    }
}
