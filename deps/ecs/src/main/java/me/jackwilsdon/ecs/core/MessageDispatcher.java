package me.jackwilsdon.ecs.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class MessageDispatcher {
    private Set<Object> listeners = new HashSet<>();

    private boolean isValidListener(Method method, Class<? extends Message> messageClass) {
        MessageListener listener = method.getAnnotation(MessageListener.class);

        if (listener == null) {
            return false;
        }

        Class<? extends Message> listenerMessageClass = listener.messageClass();
        boolean isSubclass = listener.includeSubclasses() && listenerMessageClass.isAssignableFrom(messageClass);

        if (listenerMessageClass == messageClass || isSubclass) {
            Type[] parameterTypes = method.getParameterTypes();

            return parameterTypes.length == 1 && parameterTypes[0] == messageClass;
        }

        return false;
    }

    private Method[] getListenerMethods(Object listener, Class<? extends Message> messageClass) {
        Set<Method> methods = new HashSet<>();

        for (Method method : listener.getClass().getMethods()) {
            if (isValidListener(method, messageClass)) {
                methods.add(method);
            }
        }

        return methods.toArray(new Method[methods.size()]);
    }

    public boolean addListener(Object listener) {
        return listeners.add(listener);
    }

    public boolean removeListener(Object listener) {
        return listeners.remove(listener);
    }

    public boolean dispatchMessage(Message message) {
        Class<? extends Message> messageClass = message.getClass();
        boolean dispatched = false;

        for (Object listener : listeners) {
            for (Method method : getListenerMethods(listener, messageClass)) {
                try {
                    method.invoke(listener, message);

                    dispatched = true;
                } catch (IllegalAccessException | InvocationTargetException ignored) {

                }
            }
        }

        return dispatched;
    }
}
