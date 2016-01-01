package me.jackwilsdon.skii.subsystems;

import me.jackwilsdon.ecs.core.SubSystem;

public class DebugSubSystem extends SubSystem {
    private long lastTickTime = 0;
    private float totalTickTime = 0;
    private int totalTickCount = 0;

    public DebugSubSystem() {
        super(0);
    }

    @Override
    public void onTick(float deltaTime) {
        long currentTickTime = System.nanoTime();

        if (lastTickTime == 0) {
            lastTickTime = currentTickTime;
        }

        long tickDuration = currentTickTime - lastTickTime;

        if (tickDuration != 0) {
            totalTickTime += ((float) (1000 * 1000 * 1000)) / tickDuration;
            totalTickCount++;

            System.out.println("Average TPS: " + (totalTickTime / totalTickCount));
        }

        lastTickTime = System.nanoTime();
    }

    @Override
    public void onFrame() {

    }
}
