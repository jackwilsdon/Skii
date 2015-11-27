package me.jackwilsdon.skii.systems;

import javafx.geometry.Pos;
import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.core.EntityFilter;
import me.jackwilsdon.ecs.core.FilteredEntitySystem;
import me.jackwilsdon.ecs.core.filters.ComponentTypeEntityFilter;
import me.jackwilsdon.skii.components.PlayerControlComponent;
import me.jackwilsdon.skii.components.PositionComponent;
import org.lwjgl.input.Keyboard;

public class PlayerControlEntitySystem extends FilteredEntitySystem {
    private static final EntityFilter COMPONENT_FILTER = new ComponentTypeEntityFilter(PlayerControlComponent.class, PositionComponent.class);

    public PlayerControlEntitySystem() {
        super(COMPONENT_FILTER);
    }

    @Override
    public void filteredExecute(Entity entity) {
        PlayerControlComponent controlComponent = entity.getComponent(PlayerControlComponent.class);
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);

        if (controlComponent == null || positionComponent == null || !controlComponent.enabled) {
            return;
        }

        if (Keyboard.isKeyDown(controlComponent.leftKey)) {
            positionComponent.position.x--;
        }

        if (Keyboard.isKeyDown(controlComponent.rightKey)) {
            positionComponent.position.x++;
        }
    }
}
