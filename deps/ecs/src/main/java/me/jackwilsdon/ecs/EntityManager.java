package me.jackwilsdon.ecs;

import me.jackwilsdon.ecs.component.ComponentManager;
import me.jackwilsdon.ecs.core.Component;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private int nextEntityId = 0;
    private List<Integer> entities = new ArrayList<>();
    private Engine engine;

    public EntityManager(Engine engine) {
        this.engine = engine;
    }

    public boolean hasEntity(int entityId) {
        return entities.contains(entityId);
    }

    public int createEntity() {
        int entityId = nextEntityId++;

        if (nextEntityId == Integer.MAX_VALUE) {
            return -1;
        }

        entities.add(entityId);

        return entityId;
    }

    public int[] getEntities() {
        int[] entityIds = new int[entities.size()];

        for (int i = 0; i < entities.size(); i++) {
            entityIds[i] = entities.get(i);
        }

        return entityIds;
    }

    public boolean removeEntity(int entityId) {
        if (entities.remove(Integer.valueOf(entityId))) {
            ComponentManager componentManager = engine.getComponentManager();

            for (Component component : componentManager.getComponents(entityId)) {
                componentManager.removeComponent(entityId, component);
            }

            return true;
        }

        return false;
    }
}
