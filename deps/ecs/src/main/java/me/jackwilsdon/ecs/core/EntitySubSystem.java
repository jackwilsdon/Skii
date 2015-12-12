package me.jackwilsdon.ecs.core;

public abstract class EntitySubSystem extends SubSystem {

    @Override
    public void onTick(float deltaTime) {
        for (int entityId : getEngine().getEntityManager().getEntities()) {
            onEntityTick(entityId, deltaTime);
        }
    }

    @Override
    public void onFrame() {
        for (int entityId : getEngine().getEntityManager().getEntities()) {
            onEntityFrame(entityId);
        }
    }

    public abstract void onEntityTick(int entityId, float deltaTime);

    public abstract void onEntityFrame(int entityId);
}
