package me.jackwilsdon.ecs.core;

import java.util.Comparator;

public abstract class SubSystem {
    private Engine engine;
    private int priority;

    public SubSystem(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

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

    public void onFrame() {

    }
}
