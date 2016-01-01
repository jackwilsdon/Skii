package me.jackwilsdon.skii.subsystems;

import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.subsystem.EntitySubSystem;
import me.jackwilsdon.skii.components.Ball;
import me.jackwilsdon.skii.components.InterpolatedPosition;
import me.jackwilsdon.skii.components.Paddle;
import org.lwjgl.opengl.GL11;

public class BallRendererSubSystem extends EntitySubSystem {

    public BallRendererSubSystem() {
        super(1400, Ball.class, InterpolatedPosition.class);
    }

    @Override
    public void onEntityTick(Entity entity, float deltaTime) {

    }

    @Override
    public void onEntityFrame(Entity entity) {
        Ball ball = entity.getComponent(Ball.class);
        InterpolatedPosition position = entity.getComponent(InterpolatedPosition.class);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(1, 1, 1);

        GL11.glVertex2f(position.x, position.y);
        GL11.glVertex2f(position.x + ball.width, position.y);
        GL11.glVertex2f(position.x + ball.width, position.y + ball.height);
        GL11.glVertex2f(position.x, position.y + ball.height);

        GL11.glEnd();
    }
}
