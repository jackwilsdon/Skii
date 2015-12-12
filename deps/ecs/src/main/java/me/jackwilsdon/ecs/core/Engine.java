package me.jackwilsdon.ecs.core;

public class Engine {
    private EntityManager entityManager = new EntityManager();
    private ComponentManager componentManager = new EntityComponentManager(entityManager);
    private PropertyManager propertyManager = new PropertyManager();
    private SubSystemManager subSystemManager = new SubSystemManager();

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
}
