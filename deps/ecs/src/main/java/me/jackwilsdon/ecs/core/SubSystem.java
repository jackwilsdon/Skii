package me.jackwilsdon.ecs.core;

public interface SubSystem {
    void onAddedToEngine(Engine engine);

    void onRemovedFromEngine(Engine engine);

    void onTick(float deltaTime);

    void onFrame();
}
