package me.jackwilsdon.ecs.parser;

import me.jackwilsdon.ecs.Engine;
import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.core.Entity;

import java.util.Map;

public final class EntityBuilder {

    private EntityBuilder() {

    }

    public static Entity build(Engine engine, EntityTemplate template) {
        Entity entity = engine.createEntity();

        Map<Class<? extends Component>, ComponentTemplate> components = template.getComponentTemplates();

        for (Map.Entry<Class<? extends Component>, ComponentTemplate> component : components.entrySet()) {
            Component componentInstance = ComponentBuilder.build(component.getKey(), component.getValue());

            entity.addComponent(componentInstance);
        }

        return entity;
    }
}
