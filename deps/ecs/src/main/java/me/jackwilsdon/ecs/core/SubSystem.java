package me.jackwilsdon.ecs.core;

public abstract class SubSystem {
    private Engine engine;

    public final Engine getEngine() {
        return engine;
    }

    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    public void removedFromEngine(Engine engine) {
        this.engine = null;
    }

    public void onTick(float deltaTime) {

    }
}
