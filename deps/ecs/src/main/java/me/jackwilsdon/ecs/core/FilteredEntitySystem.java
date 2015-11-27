package me.jackwilsdon.ecs.core;

public abstract class FilteredEntitySystem implements EntitySystem {
    private EntityFilter filter;

    public FilteredEntitySystem(EntityFilter filter) {
        this.filter = filter;
    }

    public final void execute(Entity entity) {
        if (filter.apply(entity)) {
            filteredExecute(entity);
        }
    }

    public abstract void filteredExecute(Entity entity);
}
