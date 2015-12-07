package me.jackwilsdon.ecs.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MessageDispatcher implements Set<Object> {
    private Set<Object> listeners = new HashSet<>();

    private boolean isValidAnnotation(MessageListener annotation, Class<? extends Message> messageClass) {
        Class<? extends Message> methodMessageClass = annotation.type();

        boolean isSameClass = messageClass == methodMessageClass;
        boolean isSubclass = messageClass.isAssignableFrom(methodMessageClass);
        boolean includeSubclasses = annotation.subclasses();

        return isSameClass || (isSubclass && includeSubclasses);
    }

    private boolean isValidMethod(Method method, Class<? extends Message> messageClass) {
        Class<?>[] argTypes = method.getParameterTypes();

        if (argTypes.length != 1 || argTypes[0] != messageClass) {
            return false;
        }

        MessageListener annotation = method.getAnnotation(MessageListener.class);

        return annotation != null && isValidAnnotation(annotation, messageClass);
    }

    private List<Method> getValidMethods(Object object, Class<? extends Message> messageClass) {
        List<Method> validMethods = new ArrayList<>();

        for (Method method : object.getClass().getMethods()) {
            if (isValidMethod(method, messageClass)) {
                validMethods.add(method);
            }
        }

        return validMethods;
    }

    @Override
    public int size() {
        return listeners.size();
    }

    @Override
    public boolean isEmpty() {
        return listeners.isEmpty();
    }

    @Override
    public boolean contains(Object object) {
        return listeners.contains(object);
    }

    @Override
    public Iterator<Object> iterator() {
        return listeners.iterator();
    }

    @Override
    public Object[] toArray() {
        return listeners.toArray();
    }

    @Override
    public <T> T[] toArray(T[] array) {
        return listeners.toArray(array);
    }

    @Override
    public boolean add(Object object) {
        if (listeners.add(object)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(Object object) {
        return listeners.remove(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return listeners.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<?> collection) {
        return listeners.addAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return listeners.retainAll(collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return listeners.removeAll(collection);
    }

    @Override
    public void clear() {
        listeners.clear();
    }

    public void dispatch(Message message) {
        for (Object listener : listeners) {
            List<Method> methods = getValidMethods(listener, message.getClass());

            for (Method method : methods) {
                try {
                    method.invoke(listener, message);
                } catch (IllegalAccessException | InvocationTargetException ignored) {

                }
            }
        }
    }
}
