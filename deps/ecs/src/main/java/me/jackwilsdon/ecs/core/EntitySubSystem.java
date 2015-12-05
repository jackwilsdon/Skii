package me.jackwilsdon.ecs.core;

import me.jackwilsdon.ecs.util.filter.AcceptAllFilter;
import me.jackwilsdon.ecs.util.filter.Filter;

import java.util.Set;

public abstract class EntitySubSystem extends SubSystem {
    private Filter<Entity> entityFilter;
    private Set<Entity> entities;

    public EntitySubSystem(Filter<Entity> entityFilter) {
        this.entityFilter = entityFilter;
    }

    public EntitySubSystem() {
        this(new AcceptAllFilter<Entity>());
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        entities = engine.getEntities(entityFilter);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);

        entities = null;
    }

    @Override
    public void onTick(float deltaTime) {
        super.onTick(deltaTime);

        for (Entity entity : entities) {
            onEntityTick(entity, deltaTime);
        }
    }

    public abstract void onEntityTick(Entity entity, float deltaTime);
}
