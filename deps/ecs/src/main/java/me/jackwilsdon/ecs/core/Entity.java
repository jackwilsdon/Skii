package me.jackwilsdon.ecs.core;

import me.jackwilsdon.ecs.util.FilteredSet;
import me.jackwilsdon.ecs.util.filter.UniqueClassSetFilter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class Entity {
    private UUID identifier = UUID.randomUUID();
    private Set<Component> components = new FilteredSet<>(new UniqueClassSetFilter<Component>(), new HashSet<Component>());

    public UUID getIdentifier() {
        return identifier;
    }

    public boolean has(Class<? extends Component> componentClass) {
        return get(componentClass) != null;
    }

    public <T extends Component> T get(Class<T> componentClass) {
        for (Component component : components) {
            if (component.getClass() == componentClass) {
                return componentClass.cast(component);
            }
        }

        return null;
    }

    public boolean add(Component component) {
        return components.add(component);
    }

    public boolean remove(Class<? extends Component> componentClass) {
        Component component = get(componentClass);

        return component != null && components.remove(component);
    }
}
