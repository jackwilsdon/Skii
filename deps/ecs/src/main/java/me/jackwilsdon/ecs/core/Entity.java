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

    public boolean hasComponent(Component component) {
        checkValidity();

        return hasComponent(component.getClass());
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

    public boolean removeComponent(Class<? extends Component> componentClass) {
        checkValidity();

        return getEngine().getComponentManager().removeComponent(getId(), componentClass);
    }

    public boolean removeComponent(Component component) {
        checkValidity();

        return removeComponent(component.getClass());
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
