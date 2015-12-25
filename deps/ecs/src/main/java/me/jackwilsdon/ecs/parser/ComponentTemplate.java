package me.jackwilsdon.ecs.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ComponentTemplate {
    private Map<String, String> fields = new HashMap<>();

    private Method getValueOfMethod(Class<?> valueClass) {
        Method valueOf;

        try {
            valueOf = valueClass.getMethod("valueOf", String.class);
        } catch (ReflectiveOperationException exception) {
            throw new IllegalArgumentException(exception);
        }

        if (!Modifier.isStatic(valueOf.getModifiers())) {
            String message = String.format("%s.valueOf(%s) is not static", valueClass.getName(),
                    String.class.getName());

            throw new IllegalArgumentException(message);
        }

        Class<?> returnType = valueOf.getReturnType();

        if (returnType != valueClass) {
            String message = String.format("%s.valueOf(%s) returns %s, expected %s", valueClass.getName(),
                    String.class.getName(), returnType.getName(), valueClass.getName());

            throw new IllegalArgumentException(message);
        }

        return valueOf;
    }

    private <T> T getValueOf(String field, Class<T> valueClass) {
        Method valueOf = getValueOfMethod(valueClass);
        Object returnedValue;

        try {
            returnedValue = valueOf.invoke(null, getField(field));
        } catch (ReflectiveOperationException exception) {
            Throwable cause = exception instanceof InvocationTargetException ? exception.getCause() : exception;

            String message = String.format("failed to call %s.valueOf(%s) for field \"%s\": %s", valueClass.getName(),
                    String.class.getName(), field, cause.toString());

            throw new IllegalArgumentException(message);
        }

        if (returnedValue == null) {
            String message = String.format("%s.valueOf(%s) returned null for field \"%s\"", valueClass.getName(),
                    String.class.getName(), field);

            throw new IllegalArgumentException(message);
        }

        return valueClass.cast(returnedValue);
    }

    public boolean hasField(String name) {
        return fields.containsKey(name);
    }

    public <T> T getField(String name, T defaultValue, Class<T> valueClass) {
        if (!hasField(name)) {
            return defaultValue;
        }

        return getValueOf(name, valueClass);
    }

    public <T> T getField(String name, Class<T> valueClass) {
        checkField(name);

        return getValueOf(name, valueClass);
    }

    public String getField(String name, String defaultValue) {
        if (!hasField(name)) {
            return defaultValue;
        }

        return fields.get(name);
    }

    public String getField(String name) {
        checkField(name);

        return fields.get(name);
    }

    public void addField(String name, String value) {
        fields.put(name, value);
    }

    public boolean removeField(String name) {
        if (!hasField(name)) {
            return false;
        }

        fields.remove(name);

        return true;
    }

    private void checkField(String name) {
        if (!hasField(name)) {
            throw new NoSuchElementException(name);
        }
    }
}
