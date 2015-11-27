package me.jackwilsdon.skii.systems;

import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.core.EntityFilter;
import me.jackwilsdon.ecs.core.FilteredEntitySystem;
import me.jackwilsdon.ecs.core.filters.ComponentTypeEntityFilter;
import me.jackwilsdon.ecs.core.filters.CompoundEntityFilter;
import me.jackwilsdon.ecs.core.filters.WorldPropertyTypeEntityFilter;
import me.jackwilsdon.skii.components.EntityTypeComponent;
import me.jackwilsdon.skii.components.PositionComponent;
import me.jackwilsdon.skii.properties.GridSizeProperty;
import me.jackwilsdon.skii.util.EntityType;
import me.jackwilsdon.skii.util.Vector2f;
import org.lwjgl.opengl.GL11;

public class EntityTypeRendererEntitySystem extends FilteredEntitySystem {
    private static final EntityFilter COMPONENT_FILTER = new ComponentTypeEntityFilter(PositionComponent.class, EntityTypeComponent.class);
    private static final EntityFilter PROPERTY_FILTER = new WorldPropertyTypeEntityFilter(GridSizeProperty.class);
    private static final EntityFilter COMPOUND_FILTER = new CompoundEntityFilter(COMPONENT_FILTER, PROPERTY_FILTER);

    public EntityTypeRendererEntitySystem() {
        super(COMPONENT_FILTER);
    }

    @Override
    public void filteredExecute(Entity entity) {
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
        EntityTypeComponent typeComponent = entity.getComponent(EntityTypeComponent.class);
        GridSizeProperty gridSizeProperty = entity.getWorldProperty(GridSizeProperty.class);

        if (positionComponent == null || typeComponent == null || gridSizeProperty == null) {
            return;
        }

        Vector2f position = positionComponent.position;
        EntityType entityType = typeComponent.entityType;
        int gridSize = gridSizeProperty.gridSize;

        GL11.glPushMatrix();
        GL11.glTranslatef((float) Math.floor(position.x) * gridSize, (float) Math.floor(position.y) * gridSize, 0);
        GL11.glBegin(GL11.GL_QUADS);

        switch (entityType) {
            case TREE:
                GL11.glColor3f(0, 0.9f, 0);
                break;
            case PLAYER:
                GL11.glColor3f(0.9f, 0, 0);
                break;
            default:
                GL11.glColor3f(0, 0, 0.9f);
                break;
        }

        GL11.glVertex2f(0, 0);
        GL11.glVertex2f(gridSize, 0);
        GL11.glVertex2f(gridSize, gridSize);
        GL11.glVertex2f(0, gridSize);

        GL11.glEnd();
        GL11.glPopMatrix();
    }
}
