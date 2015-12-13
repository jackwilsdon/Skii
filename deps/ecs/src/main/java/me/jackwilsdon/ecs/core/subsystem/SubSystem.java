package me.jackwilsdon.ecs.core.subsystem;

import me.jackwilsdon.ecs.core.Engine;

public abstract class SubSystem {
    private Engine engine;

    public Engine getEngine() {
        return engine;
    }

    public void onAddedToEngine(Engine engine) {
        if (getEngine() != null) {
            throw new UnsupportedOperationException("subsystem already added to an engine");
        }

        engine.getMessageDispatcher().addListener(this);

        this.engine = engine;
    }

    public void onRemovedFromEngine(Engine engine) {
        if (getEngine() != null) {
            throw new UnsupportedOperationException("subsystem removed from wrong engine");
        }

        engine.getMessageDispatcher().removeListener(this);

        this.engine = null;
    }

    public void onTick(float deltaTime) {

    }

    public void onFrame() {

    }
}
