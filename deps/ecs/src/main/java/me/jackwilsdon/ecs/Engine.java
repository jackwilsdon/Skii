package me.jackwilsdon.ecs;

import me.jackwilsdon.ecs.component.ComponentManager;
import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.core.Entity;
import me.jackwilsdon.ecs.core.SubSystem;
import me.jackwilsdon.ecs.message.MessageDispatcher;
import me.jackwilsdon.ecs.property.PropertyManager;
import me.jackwilsdon.ecs.subsystem.SubSystemManager;

import java.util.ArrayList;
import java.util.List;

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

    @SafeVarargs
    public final Entity[] getEntities(Class<? extends Component>... componentClasses) {
        ComponentManager componentManager = getComponentManager();
        List<Entity> entityList = new ArrayList<>();

        for (int entityId : getEntityManager().getEntities()) {
            boolean validEntity = true;

            for (Class<? extends Component> componentClass : componentClasses) {
                if (!componentManager.hasComponent(entityId, componentClass)) {
                    validEntity = false;
                    break;
                }
            }

            if (validEntity) {
                entityList.add(new Entity(entityId, this));
            }
        }

        return entityList.toArray(new Entity[entityList.size()]);
    }

    public boolean removeEntity(Entity entity) {
        return entity != null && getEntityManager().removeEntity(entity.getId());
    }
}
