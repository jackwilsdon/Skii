package me.jackwilsdon.ecs.core;

public class Engine {
    private EntityManager entityManager = new EntityManager();
    private ComponentManager componentManager = new ComponentManager();
    private PropertyManager propertyManager = new PropertyManager();

    public boolean hasEntity(int entityId) {
        return entityManager.hasEntity(entityId);
    }

    public int createEntity() {
        return entityManager.createEntity();
    }

    public int[] getEntities() {
        return entityManager.getEntities();
    }

    public boolean removeEntity(int entityId) {
        return entityManager.removeEntity(entityId);
    }

    public boolean hasComponent(int entityId, Class<? extends Component> componentClass) {
        return hasEntity(entityId) && componentManager.hasComponent(entityId, componentClass);
    }

    public boolean hasComponent(int entityId, Component component) {
        return hasEntity(entityId) && componentManager.hasComponent(entityId, component);
    }

    public Component[] getComponents(int entityId) {
        if (!hasEntity(entityId)) {
            return null;
        }

        return componentManager.getComponents(entityId);
    }

    public <T extends Component> T getComponent(int entityId, Class<T> componentClass) {
        if (!hasEntity(entityId)) {
            return null;
        }

        return componentManager.getComponent(entityId, componentClass);
    }

    public boolean addComponent(int entityId, Component component) {
        return hasEntity(entityId) && componentManager.addComponent(entityId, component);
    }

    public boolean removeComponent(int entityId, Class<? extends Component> componentClass) {
        return hasEntity(entityId) && componentManager.removeComponent(entityId, componentClass);
    }

    public boolean removeComponent(int entityId, Component component) {
        return hasEntity(entityId) && componentManager.removeComponent(entityId, component);
    }

    public boolean hasProperty(Class<? extends Property> propertyClass) {
        return propertyManager.hasProperty(propertyClass);
    }

    public boolean hasProperty(Property property) {
        return propertyManager.hasProperty(property);
    }

    public Property[] getProperties() {
        return propertyManager.getProperties();
    }

    public <T extends Property> T getProperty(Class<T> propertyClass) {
        return propertyManager.getProperty(propertyClass);
    }

    public boolean addProperty(Property property) {
        return propertyManager.addProperty(property);
    }

    public boolean removeProperty(Class<? extends Property> propertyClass) {
        return propertyManager.removeProperty(propertyClass);
    }

    public boolean removeProperty(Property property) {
        return propertyManager.removeProperty(property);
    }
}
