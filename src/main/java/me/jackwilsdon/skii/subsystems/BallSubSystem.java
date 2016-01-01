package me.jackwilsdon.skii.subsystems;

import javafx.geometry.Pos;
import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.subsystem.EntitySubSystem;
import me.jackwilsdon.skii.components.Ball;
import me.jackwilsdon.skii.components.Paddle;
import me.jackwilsdon.skii.components.Player;
import me.jackwilsdon.skii.components.Position;
import me.jackwilsdon.skii.components.Velocity;
import org.lwjgl.input.Keyboard;

public class BallSubSystem extends EntitySubSystem {

    public BallSubSystem() {
        super(0, Ball.class, Position.class, Velocity.class);
    }

    private boolean intersects(float aX, float aY, float aWidth, float aHeight, float bX, float bY, float bWidth, float bHeight) {
        return !(aX > bX + bWidth || aY > bY + bHeight || aX + aWidth < bX || aY + aHeight < bY);
    }

    private void handlePaddleCollisions(Entity ballEntity, Entity paddleEntity) {
        Ball ball = ballEntity.getComponent(Ball.class);
        Position ballPosition = ballEntity.getComponent(Position.class);
        Velocity ballVelocity = ballEntity.getComponent(Velocity.class);

        Paddle paddle = paddleEntity.getComponent(Paddle.class);
        Position paddlePosition = paddleEntity.getComponent(Position.class);

        if (intersects(ballPosition.x, ballPosition.y, ball.width, ball.height, paddlePosition.x, paddlePosition.y, paddle.width, paddle.height)) {
            ballVelocity.x = Math.abs(ballVelocity.x) * paddle.direction;
        }
    }

    @Override
    public void onEntityTick(Entity entity, float deltaTime) {
        for (Entity paddle : getEngine().getEntities(Paddle.class, Position.class)) {
            handlePaddleCollisions(entity, paddle);
        }
    }

    @Override
    public void onEntityFrame(Entity entity) {

    }
}
