package me.jackwilsdon.skii.subsystems;

import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.subsystem.EntitySubSystem;
import me.jackwilsdon.skii.components.InterpolatedPosition;
import me.jackwilsdon.skii.components.Paddle;
import org.lwjgl.opengl.GL11;

public class PaddleRendererSubSystem extends EntitySubSystem {

    public PaddleRendererSubSystem() {
        super(400, Paddle.class, InterpolatedPosition.class);
    }

    @Override
    public void onEntityTick(Entity entity, float deltaTime) {

    }

    @Override
    public void onEntityFrame(Entity entity) {
        Paddle paddle = entity.getComponent(Paddle.class);
        InterpolatedPosition position = entity.getComponent(InterpolatedPosition.class);

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(1, 1, 1);

        GL11.glVertex2f(position.x, position.y);
        GL11.glVertex2f(position.x + paddle.width, position.y);
        GL11.glVertex2f(position.x + paddle.width, position.y + paddle.height);
        GL11.glVertex2f(position.x, position.y + paddle.height);

        GL11.glEnd();
    }
}
