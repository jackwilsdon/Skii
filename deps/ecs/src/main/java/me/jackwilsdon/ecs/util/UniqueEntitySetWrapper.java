package me.jackwilsdon.ecs.util;

import me.jackwilsdon.ecs.core.Entity;

import java.util.Set;

public class UniqueEntitySetWrapper extends UniqueSetWrapper<Entity> {
    public UniqueEntitySetWrapper(Set<Entity> set) {
        super(set);
    }

    @Override
    protected Object getUniqueIdentifier(Entity entity) {
        return entity.getIdentifier().toString();
    }
}
