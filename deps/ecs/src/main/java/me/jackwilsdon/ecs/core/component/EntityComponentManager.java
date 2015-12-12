package me.jackwilsdon.ecs.core.component;

import me.jackwilsdon.ecs.core.EntityManager;

public class EntityComponentManager extends ComponentManager {
    private EntityManager entityManager;

    public EntityComponentManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean hasComponent(int entityId, Class<? extends Component> componentClass) {
        return entityManager.hasEntity(entityId) && super.hasComponent(entityId, componentClass);
    }

    @Override
    public boolean hasComponent(int entityId, Component component) {
        return entityManager.hasEntity(entityId) && super.hasComponent(entityId, component);
    }

    @Override
    public Component[] getComponents(int entityId) {
        if (!entityManager.hasEntity(entityId)) {
            return null;
        }

        return super.getComponents(entityId);
    }

    @Override
    public <T extends Component> T getComponent(int entityId, Class<T> componentClass) {
        if (!entityManager.hasEntity(entityId)) {
            return null;
        }

        return super.getComponent(entityId, componentClass);
    }

    @Override
    public boolean addComponent(int entityId, Component component) {
        return entityManager.hasEntity(entityId) && super.addComponent(entityId, component);
    }

    @Override
    public boolean removeComponent(int entityId, Class<? extends Component> componentClass) {
        return entityManager.hasEntity(entityId) && super.removeComponent(entityId, componentClass);
    }

    @Override
    public boolean removeComponent(int entityId, Component component) {
        return entityManager.hasEntity(entityId) && super.removeComponent(entityId, component);
    }
}
