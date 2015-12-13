package me.jackwilsdon.ecs.core.subsystem;

public abstract class EntitySubSystem extends SubSystem {

    public EntitySubSystem(int priority) {
        super(priority);
    }

    @Override
    public final void onTick(float deltaTime) {
        super.onTick(deltaTime);

        for (int entityId : getEngine().getEntityManager().getEntities()) {
            onEntityTick(entityId, deltaTime);
        }
    }

    @Override
    public final void onFrame() {
        super.onFrame();

        for (int entityId : getEngine().getEntityManager().getEntities()) {
            onEntityFrame(entityId);
        }
    }

    public void onEntityTick(int entityId, float deltaTime) {

    }

    public void onEntityFrame(int entityId) {}

}
