package me.jackwilsdon.ecs.core;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private int nextEntityId = 0;
    private List<Integer> entities = new ArrayList<>();

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
        return entities.remove(Integer.valueOf(entityId));
    }
}
