package me.jackwilsdon.ecs.core;

import me.jackwilsdon.ecs.util.UniqueClassSetWrapper;

import java.util.HashSet;
import java.util.UUID;

public class Entity {
    private UUID identifier = UUID.randomUUID();
    private UniqueClassSetWrapper<Component> components = new UniqueClassSetWrapper<>(new HashSet<Component>());

    public Entity(Component... components) {
        for (Component component : components) {
            add(component, false);
        }
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public boolean has(Class<? extends Component> componentClass) {
        return get(componentClass) != null;
    }

    public <T extends Component> T get(Class<T> componentClass) {
        return components.cast(componentClass);
    }

    public <T extends Component> T add(T component, boolean overwrite) {
        if (overwrite) {
            remove(component);
        }

        if (!components.add(component)) {
            return null;
        }

        return component;
    }

    public <T extends Component> T add(T component) {
        return add(component, false);
    }

    public <T extends Component> T remove(Class<T> componentClass) {
        T component = get(componentClass);

        if (!components.remove(component)) {
            return null;
        }

        return component;
    }

    public <T extends Component> T remove(T component) {
        if (!components.remove(component)) {
            return null;
        }

        return component;
    }
}
