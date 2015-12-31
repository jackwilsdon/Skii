package me.jackwilsdon.skii;

import me.jackwilsdon.ecs.Engine;
import me.jackwilsdon.ecs.core.SubSystem;
import me.jackwilsdon.skii.util.NativeLoader;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class SkiiGame extends Game {
    Engine engine = new Engine();

    @Override
    public void onStart() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.create();

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();

            GL11.glOrtho(0, 640, 480, 0, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        } catch (LWJGLException exception) {
            exception.printStackTrace();
            stop();
        }
    }

    @Override
    public void onFrame() {
        if (Display.isCloseRequested()) {
            stop();
        }

        for (SubSystem subSystem : engine.getSubSystemManager().getSubSystems()) {
            subSystem.onFrame();
        }

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
