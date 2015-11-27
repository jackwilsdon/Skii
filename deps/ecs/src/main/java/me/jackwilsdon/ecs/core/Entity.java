package me.jackwilsdon.ecs.core;

import me.jackwilsdon.ecs.util.UniqueClassSetWrapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

public final class Entity {
    private UUID identifier = UUID.randomUUID();
    private World world;
    private UniqueClassSetWrapper<Component> components = new UniqueClassSetWrapper<>(new HashSet<>());

    public Entity(World world, Component... components) {
        this.world = world;
        this.components.addAll(Arrays.asList(components));
    }

    public Entity(Component... components) {
        this(null, components);
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public World getWorld() {
        return world;
    }

    public boolean hasWorldProperty(Class<? extends Property> property) {
        return world != null && world.getProperty(property) != null;
    }

    public <T extends Property> T getWorldProperty(Class<T> propertyClass) {
        return world == null ? null : world.getProperty(propertyClass);
    }

    public boolean addWorldProperty(Property property) {
        return world != null && world.addProperty(property);
    }

    public boolean removeWorldProperty(Property property) {
        return world != null && world.removeProperty(property);
    }

    public void setWorld(World world) {
        if (this.world != null) {
            throw new UnsupportedOperationException("Cannot change world (world already set)");
        }

        this.world = world;
    }

    public boolean hasComponent(Class<? extends Component> componentClass) {
        return components.get(componentClass) != null;
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        return components.cast(componentClass);
    }

    public boolean addComponent(Component component) {
        return components.add(component);
    }

    public boolean removeComponent(Component component) {
        return components.remove(component);
    }
}
