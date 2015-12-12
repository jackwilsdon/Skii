package me.jackwilsdon.ecs.core;

public abstract class SubSystem {
    private Engine engine;

    public final Engine getEngine() {
        return engine;
    }

    public final void onAddedToEngine(Engine engine) {
        if (getEngine() != null) {
            throw new UnsupportedOperationException("subsystem already added to an engine");
        }

        this.engine = engine;
    }

    public final void onRemovedFromEngine(Engine engine) {
        if (getEngine() != null) {
            throw new UnsupportedOperationException("subsystem removed from wrong engine");
        }

        this.engine = null;
    }

    public abstract void onTick(float deltaTime);

    public abstract gvoid onFrame();
}
