package me.jackwilsdon.skii.properties;

import me.jackwilsdon.ecs.core.Property;

public class GridSizeProperty implements Property {
    public int gridSize;

    public GridSizeProperty(int gridSize) {
        this.gridSize = gridSize;
    }
}
