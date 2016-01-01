package me.jackwilsdon.skii.subsystems;

import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.subsystem.EntitySubSystem;
import me.jackwilsdon.skii.components.Paddle;
import me.jackwilsdon.skii.components.Position;
import me.jackwilsdon.skii.components.Speed;
import me.jackwilsdon.skii.components.Velocity;
import org.lwjgl.opengl.Display;

public class PaddleSubSystem extends EntitySubSystem {

    public PaddleSubSystem() {
        super(100, Paddle.class, Velocity.class, Speed.class, Position.class);
    }

    @Override
    public void onEntityTick(Entity entity, float deltaTime) {
        Paddle paddle = entity.getComponent(Paddle.class);
        Velocity velocity = entity.getComponent(Velocity.class);
        Speed speed = entity.getComponent(Speed.class);
        Position position = entity.getComponent(Position.class);

        if (paddle.moveUp) {
            velocity.y = -speed.speed;
        } else if (paddle.moveDown) {
            velocity.y = speed.speed;
        } else {
            velocity.y = 0;
        }

        position.y = Math.min(Math.max(position.y, 0), Display.getHeight() - paddle.height);
    }

    @Override
    public void onEntityFrame(Entity entity) {

    }
}
