package me.jackwilsdon.skii.subsystems;

import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.subsystem.EntitySubSystem;
import me.jackwilsdon.skii.components.Position;
import me.jackwilsdon.skii.components.Velocity;

public class MovementSubSystem extends EntitySubSystem {

    public MovementSubSystem() {
        super(0, Position.class, Velocity.class);
    }

    @Override
    public void onEntityTick(Entity entity, float deltaTime) {
        Position position = entity.getComponent(Position.class);
        Velocity velocity = entity.getComponent(Velocity.class);

        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
    }

    @Override
    public void onEntityFrame(Entity entity) {

    }
}
