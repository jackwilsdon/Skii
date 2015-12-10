package me.jackwilsdon.ecs.core.filter;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.util.filter.Filter;
import me.jackwilsdon.ecs.util.filter.context.CollectionFilterContext;

public class EntityComponentSetFilter implements Filter<Entity, CollectionFilterContext<Entity>> {
    private Class<? extends Component> componentClass;

    public EntityComponentSetFilter(Class<? extends Component> componentClass) {
        this.componentClass = componentClass;
    }

    @Override
    public boolean accept(Entity value, CollectionFilterContext<Entity> context) {
        return value.has(componentClass);
    }
}
