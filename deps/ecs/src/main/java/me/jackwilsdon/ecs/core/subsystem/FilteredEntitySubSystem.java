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

        filterContext.setEngine(engine);
    }

    @Override
    public void onRemovedFromEngine(Engine engine) {
        super.onRemovedFromEngine(engine);

        filterContext.setEngine(null);
    }

    @Override
    public final void onEntityTick(int entityId, float deltaTime) {
        super.onEntityTick(entityId, deltaTime);

        if (filter.accept(entityId, filterContext)) {
            onFilteredEntityTick(entityId, deltaTime);
        }
    }

    @Override
    public final void onEntityFrame(int entityId) {
        super.onEntityFrame(entityId);

        if (filter.accept(entityId, filterContext)) {
            onFilteredEntityFrame(entityId);
        }
    }

    public void onFilteredEntityTick(int entityId, float deltaTime) {

    }

    public void onFilteredEntityFrame(int entityId) {

    }
}
