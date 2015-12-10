package me.jackwilsdon.ecs.core;

public abstract class BaseSubSystem implements SubSystem {
    private Engine engine;

    public Engine getEngine() {
        return engine;
    }

    @Override
    public void onAddedToEngine(Engine engine) {
        if (this.engine != null) {
            throw new UnsupportedOperationException("subsystem already added to an engine");
        }

        this.engine = engine;
    }

    @Override
    public void onRemovedFromEngine(Engine engine) {
        this.engine = null;
    }
}
