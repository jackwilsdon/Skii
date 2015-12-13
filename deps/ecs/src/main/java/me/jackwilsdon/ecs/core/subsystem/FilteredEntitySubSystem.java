package me.jackwilsdon.ecs.core.subsystem;

import me.jackwilsdon.ecs.core.Engine;
import me.jackwilsdon.ecs.core.filter.EngineFilterContext;
import me.jackwilsdon.ecs.util.filter.Filter;

public abstract class FilteredEntitySubSystem extends EntitySubSystem {
    private Filter<Integer, EngineFilterContext> filter;
    private EngineFilterContext filterContext;

    public FilteredEntitySubSystem(Filter<Integer, EngineFilterContext> filter) {
        this.filter = filter;
        filterContext = new EngineFilterContext();
    }

    @Override
    public void onAddedToEngine(Engine engine) {
        super.onAddedToEngine(engine);

        filterContext.engine = engine;
    }

    @Override
    public void onRemovedFromEngine(Engine engine) {
        super.onRemovedFromEngine(engine);

        filterContext.engine = null;
    }

    @Override
    public void onEntityTick(int entityId, float deltaTime) {
        if (filter.accept(entityId, filterContext)) {
            onFilteredEntityTick(entityId, deltaTime);
        }
    }

    @Override
    public void onEntityFrame(int entityId) {
        if (filter.accept(entityId, filterContext)) {
            onFilteredEntityFrame(entityId);
        }
    }

    public abstract void onFilteredEntityTick(int entityId, float deltaTime);

    public abstract void onFilteredEntityFrame(int entityId);
}
