package me.jackwilsdon.ecs.component;

import me.jackwilsdon.ecs.Engine;
import me.jackwilsdon.ecs.EntityManager;
import me.jackwilsdon.ecs.core.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ComponentManager {
    private Map<Integer, List<Component>> entityComponents = new HashMap<>();
    private Engine engine;

    public ComponentManager(Engine engine) {
        this.engine = engine;
    }

    private List<Component> getComponentList(int entityId) {
        List<Component> componentList = entityComponents.get(entityId);

        if (!entityComponents.containsKey(entityId)) {
            componentList = new ArrayList<>();
            entityComponents.put(entityId, componentList);
        }

        return componentList;
    }

    public boolean hasComponent(int entityId, Class<? extends Component> componentClass) {
        if (!engine.getEntityManager().hasEntity(entityId)) {
            return false;
        }

        Component[] components = getComponents(entityId);

        for (Component component : components) {
            if (component.getClass() == componentClass) {
                return true;
            }
        }

        return false;
    }

    public boolean hasComponent(int entityId, Component component) {
        return hasComponent(entityId, component.getClass());
    }

    public Component[] getComponents(int entityId) {
        if (!engine.getEntityManager().hasEntity(entityId)) {
            return null;
        }

        List<Component> components = getComponentList(entityId);

        return components.toArray(new Component[components.size()]);
    }

    public <T extends Component> T getComponent(int entityId, Class<T> componentClass) {
        if (!engine.getEntityManager().hasEntity(entityId)) {
            return null;
        }

        Component[] components = getComponents(entityId);

        for (Component component : components) {
            if (component.getClass() == componentClass) {
                return componentClass.cast(component);
            }
        }

        return null;
    }

    public boolean addComponent(int entityId, Component component) {
        if (!engine.getEntityManager().hasEntity(entityId) || hasComponent(entityId, component)) {
            return false;
        }

        List<Component> components = getComponentList(entityId);

        for (Component entityComponent : components) {
            if (entityComponent.getClass() == component.getClass()) {
                return false;
            }
        }

        return components.add(component);
    }

    public boolean removeComponent(int entityId, Class<? extends Component> componentClass) {
        if (!engine.getEntityManager().hasEntity(entityId)) {
            return false;
        }

        List<Component> components = getComponentList(entityId);
        Iterator<Component> componentIterator = components.iterator();

        while (componentIterator.hasNext()) {
            Component component = componentIterator.next();

            if (component.getClass() == componentClass) {
                componentIterator.remove();
                return true;
            }
        }

        return false;
    }

    public boolean removeComponent(int entityId, Component component) {
        return removeComponent(entityId, component.getClass());
    }
}
