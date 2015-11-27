package me.jackwilsdon.ecs.core.filters;

import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.core.EntityFilter;

public class CompoundEntityFilter implements EntityFilter {
    private EntityFilter[] filters;

    public CompoundEntityFilter(EntityFilter... filters) {
        this.filters = filters;
    }

    public EntityFilter[] getFilters() {
        return filters;
    }

    public boolean apply(Entity entity) {
        for (EntityFilter filter : getFilters()) {
            if (!filter.apply(entity)) {
                return false;
            }
        }

        return true;
    }
}
