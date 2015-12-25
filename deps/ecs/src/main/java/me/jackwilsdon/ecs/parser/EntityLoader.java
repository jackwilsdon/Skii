package me.jackwilsdon.ecs.parser;

import java.util.HashMap;
import java.util.Map;

public abstract class EntityLoader {
    private Map<String, EntityTemplate> entityTemplates = new HashMap<>();

    public boolean hasEntityTemplate(String name) {
        return entityTemplates.containsKey(name);
    }

    public EntityTemplate getEntityTemplate(String name) {
        return entityTemplates.get(name);
    }

    protected void addEntityTemplate(String name, EntityTemplate template) {
        entityTemplates.put(name, template);
    }

    protected boolean removeEntityTemplate(String name) {
        if (!hasEntityTemplate(name)) {
            return false;
        }

        entityTemplates.remove(name);

        return true;
    }
}
