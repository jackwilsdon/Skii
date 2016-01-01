package me.jackwilsdon.skii.subsystems;

import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.subsystem.EntitySubSystem;
import me.jackwilsdon.skii.components.InterpolatedPosition;
import me.jackwilsdon.skii.components.Position;
import me.jackwilsdon.skii.properties.TickerProperty;
import me.jackwilsdon.skii.util.Ticker;

public class InterpolationSubSystem extends EntitySubSystem {

    public InterpolationSubSystem() {
        super(300, InterpolatedPosition.class, Position.class);
    }

    @Override
    public void onEntityTick(Entity entity, float deltaTime) {
        InterpolatedPosition interpolatedPosition = entity.getComponent(InterpolatedPosition.class);
        Position position = entity.getComponent(Position.class);

        interpolatedPosition.startX = interpolatedPosition.x;
        interpolatedPosition.startY = interpolatedPosition.y;

        interpolatedPosition.endX = position.x;
        interpolatedPosition.endY = position.y;
    }

    @Override
    public void onEntityFrame(Entity entity) {
        TickerProperty tickerProperty = getEngine().getPropertyManager().getProperty(TickerProperty.class);
        Ticker ticker = tickerProperty.ticker;

        InterpolatedPosition interpolatedPosition = entity.getComponent(InterpolatedPosition.class);

        long currentTime = System.currentTimeMillis();
        long currentProgress = currentTime - ticker.getLastTick();
        float percentThroughTick = (float) currentProgress / ticker.getTickDuration();

        float dX = interpolatedPosition.endX - interpolatedPosition.startX;
        float dY = interpolatedPosition.endY - interpolatedPosition.startY;

        interpolatedPosition.x = interpolatedPosition.startX + (dX * percentThroughTick);
        interpolatedPosition.y = interpolatedPosition.startY + (dY * percentThroughTick);
    }
}
