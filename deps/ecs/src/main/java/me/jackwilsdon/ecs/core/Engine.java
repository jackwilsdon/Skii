package me.jackwilsdon.ecs.core;

import me.jackwilsdon.ecs.core.component.ComponentManager;
import me.jackwilsdon.ecs.core.component.EntityComponentManager;
import me.jackwilsdon.ecs.core.messages.MessageDispatcher;
import me.jackwilsdon.ecs.core.property.PropertyManager;
import me.jackwilsdon.ecs.core.subsystem.EngineSubSystemManager;
import me.jackwilsdon.ecs.core.subsystem.SubSystemManager;

public class Engine {
    private EntityManager entityManager = new EntityManager();
    private ComponentManager componentManager = new EntityComponentManager(this);
    private PropertyManager propertyManager = new PropertyManager();
    private SubSystemManager subSystemManager = new EngineSubSystemManager(this);
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
}
