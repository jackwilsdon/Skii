package me.jackwilsdon.ecs.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ComponentManager {
    private Map<Integer, List<Component>> entityComponents = new HashMap<>();

    private List<Component> getComponentList(int entityId) {
        List<Component> componentList = entityComponents.get(entityId);

        if (!entityComponents.containsKey(entityId)) {
            componentList = new ArrayList<>();
            entityComponents.put(entityId, componentList);
        }

        return componentList;
    }

    public boolean hasComponent(int entityId, Class<? extends Component> componentClass) {
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
        List<Component> components = getComponentList(entityId);

        return components.toArray(new Component[components.size()]);
    }

    public <T extends Component> T getComponent(int entityId, Class<T> componentClass) {
        Component[] components = getComponents(entityId);

        for (Component component : components) {
            if (component.getClass() == componentClass) {
                return componentClass.cast(component);
            }
        }

        return null;
    }

    public boolean addComponent(int entityId, Component component) {
        return !hasComponent(entityId, component) && getComponentList(entityId).add(component);

    }

    public boolean removeComponent(int entityId, Class<? extends Component> componentClass) {
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
