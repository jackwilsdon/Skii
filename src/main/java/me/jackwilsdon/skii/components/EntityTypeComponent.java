package me.jackwilsdon.skii.components;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.skii.util.EntityType;

public class EntityTypeComponent implements Component {
    public EntityType entityType;

    public EntityTypeComponent(EntityType entityType) {
        this.entityType = entityType;
    }
}
