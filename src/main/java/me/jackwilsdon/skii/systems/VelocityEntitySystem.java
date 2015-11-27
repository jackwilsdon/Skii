package me.jackwilsdon.skii.systems;

import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.core.EntityFilter;
import me.jackwilsdon.ecs.core.FilteredEntitySystem;
import me.jackwilsdon.ecs.core.Message;
import me.jackwilsdon.ecs.core.MessageListener;
import me.jackwilsdon.ecs.core.filters.ComponentTypeEntityFilter;
import me.jackwilsdon.skii.components.PositionComponent;
import me.jackwilsdon.skii.components.VelocityComponent;
import me.jackwilsdon.skii.util.Vector2f;

public class VelocityEntitySystem extends FilteredEntitySystem {
    private static final EntityFilter COMPONENT_FILTER = new ComponentTypeEntityFilter(PositionComponent.class, VelocityComponent.class);

    public VelocityEntitySystem() {
        super(COMPONENT_FILTER);
    }

    @Override
    public void filteredExecute(Entity entity) {
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
        VelocityComponent velocityComponent = entity.getComponent(VelocityComponent.class);

        if (positionComponent == null || velocityComponent == null) {
            return;
        }

        Vector2f position = positionComponent.position;
        Vector2f velocity = velocityComponent.velocity;

        position.add(velocity);
    }
}
