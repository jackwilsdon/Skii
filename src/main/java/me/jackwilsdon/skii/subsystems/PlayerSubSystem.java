package me.jackwilsdon.skii.subsystems;

import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.subsystem.EntitySubSystem;
import me.jackwilsdon.skii.components.Paddle;
import me.jackwilsdon.skii.components.Player;
import org.lwjgl.input.Keyboard;

public class PlayerSubSystem extends EntitySubSystem {

    public PlayerSubSystem() {
        super(0, Player.class, Paddle.class);
    }

    @Override
    public void onEntityTick(Entity entity, float deltaTime) {

    }

    @Override
    public void onEntityFrame(Entity entity) {
        Player player = entity.getComponent(Player.class);
        Paddle paddle = entity.getComponent(Paddle.class);

        boolean up = Keyboard.isKeyDown(player.upKey);
        boolean down = Keyboard.isKeyDown(player.downKey);

        if (up == down) {
            paddle.moveUp = false;
            paddle.moveDown = false;
        } else if (up) {
            paddle.moveUp = true;
            paddle.moveDown = false;
        } else {
            paddle.moveUp = false;
            paddle.moveDown = true;
        }
    }
}
