package me.jackwilsdon.ecs.parser;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.core.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityTemplate {
    private Map<Class<? extends Component>, ComponentTemplate> componentTemplates = new HashMap<>();

    public boolean hasComponentTemplate(Class<? extends Component> componentClass) {
        return componentTemplates.containsKey(componentClass);
    }

    public ComponentTemplate getComponentTemplate(Class<? extends Component> componentClass) {
        return componentTemplates.get(componentClass);
    }

    public Map<Class<? extends Component>, ComponentTemplate> getComponentTemplates() {
        Map<Class<? extends Component>, ComponentTemplate> componentTemplateCopy = new HashMap<>();

        componentTemplateCopy.putAll(componentTemplates);

        return componentTemplateCopy;
    }

    public void addComponentTemplate(Class<? extends Component> componentClass, ComponentTemplate template) {
        componentTemplates.put(componentClass, template);
    }

    public boolean removeComponentTemplate(Class<? extends Component> componentClass) {
        if (!hasComponentTemplate(componentClass)) {
            return false;
        }

        componentTemplates.remove(componentClass);

        return true;
    }
}
