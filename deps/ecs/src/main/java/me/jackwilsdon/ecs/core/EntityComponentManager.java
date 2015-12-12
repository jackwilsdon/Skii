package me.jackwilsdon.ecs.core;

public class EntityComponentManager extends ComponentManager {
    private EntityManager entityManager;

    public EntityComponentManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean hasComponent(int entityId, Class<? extends Component> componentClass) {
        return entityManager.hasEntity(entityId) && hasComponent(entityId, componentClass);
    }

    public boolean hasComponent(int entityId, Component component) {
        return entityManager.hasEntity(entityId) && hasComponent(entityId, component);
    }

    public Component[] getComponents(int entityId) {
        if (!entityManager.hasEntity(entityId)) {
            return null;
        }

        return getComponents(entityId);
    }

    public <T extends Component> T getComponent(int entityId, Class<T> componentClass) {
        if (!entityManager.hasEntity(entityId)) {
            return null;
        }

        return getComponent(entityId, componentClass);
    }

    public boolean addComponent(int entityId, Component component) {
        return entityManager.hasEntity(entityId) && addComponent(entityId, component);
    }

    public boolean removeComponent(int entityId, Class<? extends Component> componentClass) {
        return entityManager.hasEntity(entityId) && removeComponent(entityId, componentClass);
    }

    public boolean removeComponent(int entityId, Component component) {
        return entityManager.hasEntity(entityId) && removeComponent(entityId, component);
    }
}
