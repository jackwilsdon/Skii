package me.jackwilsdon.ecs;

import me.jackwilsdon.ecs.component.ComponentManager;
import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.message.MessageDispatcher;
import me.jackwilsdon.ecs.property.PropertyManager;
import me.jackwilsdon.ecs.subsystem.SubSystemManager;

public final class Engine {
    private EntityManager entityManager = new EntityManager(this);
    private ComponentManager componentManager = new ComponentManager(this);
    private PropertyManager propertyManager = new PropertyManager();
    private SubSystemManager subSystemManager = new SubSystemManager(this);
    private MessageDispatcher messageDispatcher = new MessageDispatcher();

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ComponentManager getComponentManager() {
        return componentManager;
    }

    public PropertyManager getPropertyManager() {
        return propertyManager;
    }

    public SubSystemManager getSubSystemManager() {
        return subSystemManager;
    }

    public MessageDispatcher getMessageDispatcher() {
        return messageDispatcher;
    }

    public boolean hasEntity(Entity entity) {
        return entity != null && getEntityManager().hasEntity(entity.getId());
    }

    public Entity createEntity() {
        int entityId = getEntityManager().createEntity();

        return new Entity(entityId, this);
    }

    public Entity[] getEntities() {
        int[] entityIds = getEntityManager().getEntities();
        Entity[] entities = new Entity[entityIds.length];

        for (int i = 0; i < entityIds.length; i++) {
            entities[i] = new Entity(entityIds[i], this);
        }

        return entities;
    }

    public boolean removeEntity(Entity entity) {
        return entity != null && getEntityManager().removeEntity(entity.getId());
    }
}
