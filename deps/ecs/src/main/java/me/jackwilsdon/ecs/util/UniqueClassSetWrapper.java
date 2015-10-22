package me.jackwilsdon.ecs.util;

import java.util.Set;

public class UniqueClassSetWrapper<E> extends UniqueSetWrapper<E> {
    public UniqueClassSetWrapper(Set<E> set) {
        super(set);
    }

    public <T extends E> T cast(Class<? extends T> castClass) {
        E value = get(castClass);

        if (!castClass.isInstance(value)) {
            return null;
        }

        return castClass.cast(value);
    }

    @Override
    protected Object getUniqueIdentifier(E element) {
        return element.getClass();
    }
}
