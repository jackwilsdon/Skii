package me.jackwilsdon.ecs.util.filter;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.core.Entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ComponentFilter implements Filter<Entity> {
    private Set<Class<? extends Component>> componentClasses = new HashSet<>();

    @SafeVarargs
    public ComponentFilter(Class<? extends Component>... componentClasses) {
        Collections.addAll(this.componentClasses, componentClasses);
    }

    @Override
    public boolean accept(Entity entity) {
        for (Class<? extends Component> componentClass : componentClasses) {
            if (!entity.has(componentClass)) {
                return false;
            }
        }

        return true;
    }
}
