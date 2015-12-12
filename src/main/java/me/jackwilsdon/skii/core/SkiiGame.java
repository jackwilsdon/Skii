package me.jackwilsdon.skii.core;

import me.jackwilsdon.ecs.core.TickingEngine;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class SkiiGame extends Game {
    private static SkiiGame INSTANCE = new SkiiGame();

    private TickingEngine engine = new TickingEngine(20);

    @Override
    public void onStart() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.create();

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();

            GL11.glOrtho(0, 640, 480, 0, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        } catch (LWJGLException e) {
            e.printStackTrace();
            stop();
        }
    }

    @Override
    public void onFrame() {
        if (Display.isCloseRequested()) {
            stop();

            return;
        }

        engine.onFrame();
    }

    @Override
    public void onStop() {
        Display.destroy();
    }

    public static SkiiGame getInstance() {
        return INSTANCE;
    }
}
