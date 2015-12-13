package me.jackwilsdon.ecs.core.filter;

import me.jackwilsdon.ecs.core.component.Component;
import me.jackwilsdon.ecs.core.component.ComponentManager;
import me.jackwilsdon.ecs.core.component.EntityComponentManager;
import me.jackwilsdon.ecs.util.filter.Filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityComponentFilter implements Filter<Integer,EngineFilterContext> {
    private List<Class<? extends Component>> componentClasses = new ArrayList<>();

    @SafeVarargs
    public EntityComponentFilter(Class<? extends Component>... componentClasses) {
        Collections.addAll(this.componentClasses, componentClasses);
    }

    @Override
    public boolean accept(Integer value, EngineFilterContext context) {
        ComponentManager componentManager = context.engine.getComponentManager();

        for (Class<? extends Component> componentClass : componentClasses) {
            if (!componentManager.hasComponent(value, componentClass)) {
                return false;
            }
        }

        return true;
    }
}
