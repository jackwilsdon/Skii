package me.jackwilsdon.ecs.core.filters;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.core.EntityFilter;
import me.jackwilsdon.ecs.core.Property;
import me.jackwilsdon.ecs.core.World;

public class WorldPropertyTypeEntityFilter implements EntityFilter {
    private Class<? extends Property>[] propertyTypes;

    @SafeVarargs
    public WorldPropertyTypeEntityFilter(Class<? extends Property>... propertyTypes) {
        this.propertyTypes = propertyTypes;
    }

    public Class<? extends Property>[] getPropertyTypes() {
        return propertyTypes;
    }

    @Override
    public boolean apply(Entity entity) {
        World entityWorld = entity.getWorld();

        for (Class<? extends Property> propertyType : getPropertyTypes()) {
            if (!entityWorld.hasProperty(propertyType)) {
                return false;
            }
        }

        return true;
    }
}
