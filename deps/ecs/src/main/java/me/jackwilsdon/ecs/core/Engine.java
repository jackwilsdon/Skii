package me.jackwilsdon.ecs.core;

public class Engine {
    private EntityManager entityManager = new EntityManager();
    private ComponentManager componentManager = new EntityComponentManager(entityManager);
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
