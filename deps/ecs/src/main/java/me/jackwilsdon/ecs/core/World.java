package me.jackwilsdon.ecs.core;

import me.jackwilsdon.ecs.util.UniqueClassSetWrapper;
import me.jackwilsdon.ecs.util.UniqueEntitySetWrapper;

import java.util.HashSet;
import java.util.UUID;

public final class World {
    private UniqueClassSetWrapper<Property> properties = new UniqueClassSetWrapper<>(new HashSet<>());
    private UniqueEntitySetWrapper entities = new UniqueEntitySetWrapper(new HashSet<>());
    private UniqueClassSetWrapper<EntitySystem> systems = new UniqueClassSetWrapper<>(new HashSet<>());
    private MessageDispatcher dispatcher = new MessageDispatcher();

    public boolean hasProperty(Class<? extends Property> property) {
        return properties.get(property) != null;
    }

    public <T extends Property> T getProperty(Class<T> propertyClass) {
        return properties.cast(propertyClass);
    }

    public boolean addProperty(Property property) {
        return properties.add(property);
    }

    public boolean removeProperty(Property property) {
        return properties.remove(property);
    }

    public boolean hasEntity(Entity entity) {
        return entities.contains(entity);
    }

    public Entity getEntity(UUID identifier) {
        return entities.get(identifier);
    }

    public boolean addEntity(Entity entity) {
        entity.setWorld(this);

        return entities.add(entity);
    }

    public boolean removeEntity(Entity entity) {
        return entities.remove(entity);
    }

    public Entity[] getEntities() {
        return entities.toArray(new Entity[entities.size()]);
    }

    public boolean hasSystem(Class<? extends EntitySystem> systemClass) {
        return systems.get(systemClass) != null;
    }

    public <T extends EntitySystem> T getSystem(Class<T> systemClass) {
        return systems.cast(systemClass);
    }

    public EntitySystem[] getSystems() {
        return systems.toArray(new EntitySystem[systems.size()]);
    }

    public boolean addSystem(EntitySystem system) {
        boolean added = systems.add(system);

        dispatcher.addListener(system);

        return added;
    }

    public boolean removeSystem(EntitySystem system) {
        boolean removed = systems.remove(system);

        dispatcher.removeListener(system);

        return removed;
    }

    public void runSystem(Class<? extends EntitySystem> systemClass) {
        if (systemClass == null) {
            for (EntitySystem system : systems) {
                for (Entity entity : entities) {
                    system.execute(entity);
                }
            }
        } else {
            EntitySystem system = getSystem(systemClass);

            if (system != null) {
                for (Entity entity : entities) {
                    system.execute(entity);
                }
            }
        }
    }

    public void dispatchMessage(Message message) {
        dispatcher.dispatchMessage(message);
    }
}
