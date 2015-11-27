package me.jackwilsdon.ecs.core.filters;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.core.EntityFilter;

public class ComponentTypeEntityFilter implements EntityFilter {
    private Class<? extends Component>[] componentTypes;

    @SafeVarargs
    public ComponentTypeEntityFilter(Class<? extends Component>... componentTypes) {
        this.componentTypes = componentTypes;
    }

    public Class<? extends Component>[] getComponentTypes() {
        return componentTypes;
    }

    @Override
    public boolean apply(Entity entity) {
        for (Class<? extends Component> componentType : getComponentTypes()) {
            if (!entity.hasComponent(componentType)) {
                return false;
            }
        }

        return true;
    }
}
