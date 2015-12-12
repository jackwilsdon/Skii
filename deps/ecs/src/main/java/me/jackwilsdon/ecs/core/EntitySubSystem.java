package me.jackwilsdon.ecs.core;

public abstract class EntitySubSystem extends SubSystem {

    @Override
    public void onTick(float deltaTime) {
        for (int entityId : getEngine().getEntities()) {
            onEntityTick(entityId, deltaTime);
        }
    }

    @Override
    public void onFrame() {
        for (int entityId : getEngine().getEntities()) {
            onEntityFrame(entityId);
        }
    }

    public abstract void onEntityTick(int entityId, float deltaTime);

    public abstract void onEntityFrame(int entityId);
}
