package me.jackwilsdon.skii;

import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.core.EntitySystem;
import me.jackwilsdon.ecs.core.World;
import me.jackwilsdon.skii.components.EntityTypeComponent;
import me.jackwilsdon.skii.components.PlayerControlComponent;
import me.jackwilsdon.skii.components.PositionComponent;
import me.jackwilsdon.skii.components.VelocityComponent;
import me.jackwilsdon.skii.properties.GridSizeProperty;
import me.jackwilsdon.skii.systems.EntityTypeRendererEntitySystem;
import me.jackwilsdon.skii.systems.PlayerControlEntitySystem;
import me.jackwilsdon.skii.systems.VelocityEntitySystem;
import me.jackwilsdon.skii.util.EntityType;
import me.jackwilsdon.skii.util.Vector2f;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class SkiiGame extends Game {
    private World world = new World();

    private EntitySystem entityRendererSystem = new EntityTypeRendererEntitySystem();
    private EntitySystem velocitySystem = new VelocityEntitySystem();
    private EntitySystem playerControlSystem = new PlayerControlEntitySystem();

    public SkiiGame() {
        super(20);
    }

    @Override
    public boolean onLoad() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.create();

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();

            GL11.glOrtho(0, 640, 480, 0, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);

            world.addSystem(entityRendererSystem);
            world.addSystem(velocitySystem);
            world.addSystem(playerControlSystem);

            world.addProperty(new GridSizeProperty(32));

            Entity player = new Entity();

            player.addComponent(new EntityTypeComponent(EntityType.PLAYER));
            player.addComponent(new PositionComponent(Vector2f.random(5, 5)));
            player.addComponent(new PlayerControlComponent());

            world.addEntity(player);

            Entity tree = new Entity();

            tree.addComponent(new EntityTypeComponent(EntityType.TREE));
            tree.addComponent(new PositionComponent(Vector2f.random(5, 5)));
            tree.addComponent(new VelocityComponent());

            world.addEntity(tree);

        } catch (LWJGLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public void onTick() {
        world.runSystem(VelocityEntitySystem.class);
        world.runSystem(PlayerControlEntitySystem.class);
    }

    @Override
    public void onFrame() {
        if (Display.isCloseRequested()) {
            stop();
            return;
        }

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        world.runSystem(EntityTypeRendererEntitySystem.class);

        Display.update();
    }

    @Override
    public void onUnload() {
        Display.destroy();
    }
}
