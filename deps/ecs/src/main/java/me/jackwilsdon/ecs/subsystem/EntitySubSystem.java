package me.jackwilsdon.ecs.subsystem;

import me.jackwilsdon.ecs.component.ComponentManager;
import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.core.SubSystem;

public abstract class EntitySubSystem extends SubSystem {
    private Class<? extends Component>[] componentClasses;

    @SafeVarargs
    public EntitySubSystem(int priority, Class<? extends Component>... componentClasses) {
        super(priority);

        this.componentClasses = componentClasses;
    }

    private boolean shouldExecute(Entity entity) {
        return entity.hasComponents(componentClasses);
    }

    @Override
    public final void onTick(float deltaTime) {
        for (Entity entity : getEngine().getEntities()) {
            if (shouldExecute(entity)) {
                onEntityTick(entity, deltaTime);
            }
        }
    }

    @Override
    public final void onFrame() {
        for (Entity entity : getEngine().getEntities()) {
            if (shouldExecute(entity)) {
                onEntityFrame(entity);
            }
        }
    }

    public abstract void onEntityTick(Entity entity, float deltaTime);

    public abstract void onEntityFrame(Entity entity);
}
