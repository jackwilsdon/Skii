package me.jackwilsdon.ecs.core;

import me.jackwilsdon.ecs.util.UniqueClassSetWrapper;
import me.jackwilsdon.ecs.util.filter.AcceptAllFilter;
import me.jackwilsdon.ecs.util.filter.Filter;
import me.jackwilsdon.ecs.util.filter.FilteredSetWrapper;
import me.jackwilsdon.ecs.util.UniqueEntitySetWrapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Engine {
    private UniqueEntitySetWrapper entities = new UniqueEntitySetWrapper(new HashSet<Entity>());
    private UniqueClassSetWrapper<SubSystem> subSystems = new UniqueClassSetWrapper<>(new HashSet<SubSystem>());

    public Set<Entity> getEntities(Filter<Entity> entityFilter) {
        return new FilteredSetWrapper<>(entities, entityFilter);
    }

    public Set<Entity> getEntities() {
        return getEntities(new AcceptAllFilter<Entity>());
    }

    public boolean addEntity(Entity entity) {
        return entities.add(entity);
    }

    public boolean removeEntity(Entity entity) {
        return entities.remove(entity);
    }

    public Set<SubSystem> getSubSystems() {
        return Collections.unmodifiableSet(subSystems);
    }
}
