package me.jackwilsdon.skii;

import me.jackwilsdon.ecs.Engine;
import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.property.PropertyManager;
import me.jackwilsdon.ecs.subsystem.SubSystemManager;
import me.jackwilsdon.skii.components.Ball;
import me.jackwilsdon.skii.components.InterpolatedPosition;
import me.jackwilsdon.skii.components.Paddle;
import me.jackwilsdon.skii.components.Player;
import me.jackwilsdon.skii.components.Position;
import me.jackwilsdon.skii.components.Speed;
import me.jackwilsdon.skii.components.Velocity;
import me.jackwilsdon.skii.properties.TickerProperty;
import me.jackwilsdon.skii.subsystems.BallRendererSubSystem;
import me.jackwilsdon.skii.subsystems.BallSubSystem;
import me.jackwilsdon.skii.subsystems.InterpolationSubSystem;
import me.jackwilsdon.skii.subsystems.MovementSubSystem;
import me.jackwilsdon.skii.subsystems.PaddleRendererSubSystem;
import me.jackwilsdon.skii.subsystems.PaddleSubSystem;
import me.jackwilsdon.skii.subsystems.PlayerSubSystem;
import me.jackwilsdon.skii.util.NativeLoader;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class SkiiGame extends TickGame {
    private Engine engine = new Engine();

    public SkiiGame() {
        super(20);
    }

    private void setupEngine() {
        PropertyManager propertyManager = engine.getPropertyManager();

        propertyManager.addProperty(new TickerProperty(getTicker()));

        SubSystemManager subSystemManager = engine.getSubSystemManager();

        subSystemManager.addSubSystem(new MovementSubSystem());
        subSystemManager.addSubSystem(new PaddleSubSystem());
        subSystemManager.addSubSystem(new PlayerSubSystem());
        subSystemManager.addSubSystem(new PaddleRendererSubSystem());
        subSystemManager.addSubSystem(new InterpolationSubSystem());
        subSystemManager.addSubSystem(new BallRendererSubSystem());
        subSystemManager.addSubSystem(new BallSubSystem());
        //subSystemManager.addSubSystem(new DebugSubSystem());

        Entity player = engine.createEntity();

        player.addComponent(new Paddle(50, 50, 1));
        player.addComponent(new InterpolatedPosition(0, 0));
        player.addComponent(new Position(0, 0));
        player.addComponent(new Velocity(0, 0));
        player.addComponent(new Player(Keyboard.KEY_W, Keyboard.KEY_S));
        player.addComponent(new Speed(20));

        Entity ball = engine.createEntity();

        ball.addComponent(new Ball(25, 25));
        ball.addComponent(new InterpolatedPosition(100, 100));
        ball.addComponent(new Position(500, 0));
        ball.addComponent(new Velocity(-20, 0));
    }

    @Override
    public void onStart() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setVSyncEnabled(true);
            Display.create();

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();

            GL11.glOrtho(0, 640, 480, 0, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);

            setupEngine();
        } catch (LWJGLException exception) {
            exception.printStackTrace();
            stop();
        }
    }

    @Override
    public void onTick(float deltaTime) {
        engine.onTick(deltaTime);
    }

    @Override
    public void onFrame() {
        super.onFrame();

        if (Display.isCloseRequested()) {
            stop();

            return;
        }

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        engine.onFrame();

        Display.update();
    }

    @Override
    public void onStop() {
        Display.destroy();
    }

    public static void main(String[] args) throws IOException {
        NativeLoader.getInstance().load();

        new SkiiGame().start();
    }
}
