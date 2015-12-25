package me.jackwilsdon.ecs.core;

import me.jackwilsdon.ecs.Engine;
import me.jackwilsdon.ecs.exceptions.InvalidReferenceException;

public final class Entity {
    private int id;
    private Engine engine;

    public Entity(int id, Engine engine) {
        this.id = id;
        this.engine = engine;

        checkValidity();
    }

    public Entity(Engine engine) {
        this.id = -1;
        this.engine = engine;
    }

    public int getId() {
        return id;
    }

    public Engine getEngine() {
        return engine;
    }

    public boolean hasComponent(Class<? extends Component> componentClass) {
        checkValidity();

        return getEngine().getComponentManager().hasComponent(getId(), componentClass);
    }

    @SafeVarargs
    public final boolean hasComponents(Class<? extends Component>... componentClasses) {
        checkValidity();

        for (Class<? extends Component> componentClass : componentClasses) {
            if (!hasComponent(componentClass)) {
                return false;
            }
        }

        return true;
    }

    public boolean hasComponent(Component component) {
        checkValidity();

        return hasComponent(component.getClass());
    }

    public boolean hasComponents(Component... components) {
        checkValidity();

        for (Component component : components) {
            if (!hasComponent(component)) {
                return false;
            }
        }

        return true;
    }

    public Component[] getComponents() {
        checkValidity();

        return getEngine().getComponentManager().getComponents(getId());
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        checkValidity();

        return getEngine().getComponentManager().getComponent(getId(), componentClass);
    }

    public boolean addComponent(Component component) {
        checkValidity();

        return getEngine().getComponentManager().addComponent(getId(), component);
    }

    public boolean addComponents(Component... components) {
        checkValidity();

        boolean changed = false;

        for (Component component : components) {
            changed = addComponent(component) || changed;
        }

        return changed;
    }

    public boolean removeComponent(Class<? extends Component> componentClass) {
        checkValidity();

        return getEngine().getComponentManager().removeComponent(getId(), componentClass);
    }

    @SafeVarargs
    public final boolean removeComponents(Class<? extends Component>... componentClasses) {
        checkValidity();

        boolean changed = false;

        for (Class<? extends Component> componentClass : componentClasses) {
            changed = removeComponent(componentClass) || changed;
        }

        return changed;
    }

    public boolean removeComponent(Component component) {
        checkValidity();

        return removeComponent(component.getClass());
    }

    public boolean removeComponents(Component... components) {
        checkValidity();

        boolean changed = false;

        for (Component component : components) {
            changed = removeComponent(component) || changed;
        }

        return changed;
    }

    public boolean remove() {
        checkValidity();

        return getEngine().getEntityManager().removeEntity(getId());
    }

    public boolean isValid() {
        return engine != null && engine.getEntityManager().hasEntity(getId());
    }

    private void checkValidity() {
        if (!isValid()) {
            throw new InvalidReferenceException();
        }
    }
}
