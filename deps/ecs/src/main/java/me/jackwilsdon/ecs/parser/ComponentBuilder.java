package me.jackwilsdon.ecs.parser;

import me.jackwilsdon.ecs.core.Component;
import me.jackwilsdon.ecs.exceptions.InvalidTemplateException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class ComponentBuilder {

    private ComponentBuilder() {

    }

    private static <T extends Component> Constructor<T> getTemplateConstructor(Class<T> componentClass) {
        Constructor<T> constructor = null;
        NoSuchMethodException cachedException = null;

        try {
            constructor = componentClass.getConstructor(ComponentTemplate.class);
        } catch (NoSuchMethodException exception) {
            cachedException = exception;
        }

        if (constructor == null) {
            try {
                constructor = componentClass.getConstructor();
            } catch (NoSuchMethodException exception) {
                throw new InvalidTemplateException(cachedException);
            }
        }

        constructor.setAccessible(true);

        return constructor;
    }

    private static <T extends Component> T fromTemplate(Class<T> componentClass, ComponentTemplate template) {
        Constructor<T> constructor = getTemplateConstructor(componentClass);

        try {
            if (constructor.getParameterCount() == 0) {
                return constructor.newInstance();
            }

            return constructor.newInstance(template);
        } catch (ReflectiveOperationException exception) {
            Throwable cause = exception instanceof InvocationTargetException ? exception.getCause() : exception;

            throw new InvalidTemplateException(cause);
        }
    }

    public static <T extends Component> T build(Class<T> componentClass, ComponentTemplate template) {
        return fromTemplate(componentClass, template);
    }
}
