package me.jackwilsdon.ecs.core.subsystem;

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

    public void onEntityTick(int entityId, float deltaTime) {

    }

    public void onEntityFrame(int entityId) {}

}
