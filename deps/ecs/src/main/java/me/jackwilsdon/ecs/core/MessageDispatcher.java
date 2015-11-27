package me.jackwilsdon.ecs.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class MessageDispatcher {
    private Set<Object> listeners = new HashSet<>();

    public void addListener(Object listener) {
        listeners.add(listener);
    }

    public void removeListener(Object listener) {
        listeners.remove(listener);
    }

    private boolean isValidListenerMethod(Method method, Message message) {
        MessageListener[] annotations = method.getAnnotationsByType(MessageListener.class);

        Class<? extends Message> annotationClass = null;

        for (MessageListener annotation : annotations) {
            if (annotation.type().isAssignableFrom(message.getClass())) {
                if (annotation.type() == message.getClass() || annotation.subclasses()) {
                    annotationClass = annotation.type();

                    break;
                }
            }
        }

        if (annotationClass != null) {
            Class<?>[] parameterTypes = method.getParameterTypes();

            if (parameterTypes.length == 1 && parameterTypes[0] == annotationClass) {
                return true;
            }
        }

        return false;
    }

    public void dispatchMessage(Message message) {
        for (Object listener : listeners) {
            for (Method method : listener.getClass().getMethods()) {
                if (isValidListenerMethod(method, message)) {
                    try {
                        method.setAccessible(true);
                        method.invoke(listener, message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
