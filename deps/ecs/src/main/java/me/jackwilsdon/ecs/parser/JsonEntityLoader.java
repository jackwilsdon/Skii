package me.jackwilsdon.ecs.parser;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.exceptions.InvalidTemplateException;

public class JsonEntityLoader extends FileEntityLoader {
    private static final String PACKAGE_KEY = "_Package";

    private String getJsonValueAsString(JsonValue value) {
        if (!value.isObject() && !value.isArray()) {
            return value.toString();
        }

        return null;
    }

    private ComponentTemplate createComponentTemplate(String entityName, String componentName, JsonValue componentValue) {
        if (!componentValue.isObject()) {
            String message = String.format("type of %s.%s must be an object", entityName, componentName);

            throw new InvalidTemplateException(message);
        }

        ComponentTemplate template = new ComponentTemplate();

        for (JsonObject.Member componentField : componentValue.asObject()) {
            String componentFieldName = componentField.getName();
            JsonValue componentFieldValue = componentField.getValue();
            String componentFieldStringValue = getJsonValueAsString(componentFieldValue);

            if (componentFieldStringValue == null) {
                String message = String.format("type of %s.%s unsupported", entityName, componentName);

                throw new InvalidTemplateException(message);
            }

            template.addField(componentFieldName, componentFieldStringValue);
        }

        return template;
    }

    private String getPackageName(String entityName, JsonValue entityValue) {
        if (!entityValue.isObject()) {
            String message = String.format("type of %s must be an object", entityName);

            throw new InvalidTemplateException(message);
        }

        for (JsonObject.Member entityComponent : entityValue.asObject()) {
            String componentName = entityComponent.getName();
            JsonValue componentValue = entityComponent.getValue();

            if (componentName.equals(PACKAGE_KEY)) {
                if (!componentValue.isString()) {
                    String message = String.format("type of %s.%s must be a string", entityName, componentName);

                    throw new InvalidTemplateException(message);
                }

                return componentValue.asString();
            }
        }

        String message = String.format("missing %s.%s property", entityName, PACKAGE_KEY);

        throw new InvalidTemplateException(message);
    }

    private Class<? extends Component> getComponentClass(String packageName, String componentName) {
        String className = String.format("%s.%s", packageName, componentName);

        try {
            Class<?> componentClass = Class.forName(className);

            if (!Component.class.isAssignableFrom(componentClass)) {
                String message = String.format("%s is not a subclass of %s", componentClass.getName(),
                        Component.class.getName());

                throw new InvalidTemplateException(message);
            }

            return componentClass.asSubclass(Component.class);
        } catch (ClassNotFoundException e) {
            String message = String.format("unable to find class %s", className);

            throw new InvalidTemplateException(message);
        }
    }

    private EntityTemplate createEntityTemplate(String entityName, JsonValue entityValue) {
        if (!entityValue.isObject()) {
            String message = String.format("type of %s must be an object", entityName);

            throw new InvalidTemplateException(message);
        }

        EntityTemplate template = new EntityTemplate();
        String packageName = getPackageName(entityName, entityValue);

        for (JsonObject.Member entityComponent : entityValue.asObject()) {
            String componentName = entityComponent.getName();
            JsonValue componentValue = entityComponent.getValue();

            if (componentName.equals(PACKAGE_KEY)) {
                continue;
            }

            Class<? extends Component> componentClass = getComponentClass(packageName, componentName);
            ComponentTemplate componentTemplate = createComponentTemplate(entityName, componentName, componentValue);

            template.addComponentTemplate(componentClass, componentTemplate);
        }

        return template;
    }

    @Override
    public void loadFromString(String contents) {
        JsonValue value = Json.parse(contents);

        if (!value.isObject()) {
            throw new InvalidTemplateException("top level must be an object");
        }

        JsonObject object = value.asObject();

        for (JsonObject.Member member : object) {
            String entityName = member.getName();
            JsonValue entityValue = member.getValue();

            EntityTemplate entityTemplate = createEntityTemplate(entityName, entityValue);

            addEntityTemplate(entityName, entityTemplate);
        }
    }
}
