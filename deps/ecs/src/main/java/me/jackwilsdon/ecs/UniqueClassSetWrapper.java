package me.jackwilsdon.ecs;

import java.util.Set;

public class UniqueClassSetWrapper<E> extends UniqueSetWrapper<E> {
    public UniqueClassSetWrapper(Set<E> s) {
        super(s);
    }

    public <T extends E> T cast(Class<T> c) {
        E value = get(c);

        if (!c.isInstance(value)) {
            return null;
        }

        return c.cast(value);
    }

    @Override
    protected Object getUniqueIdentifier(E e) {
        return e.getClass();
    }
}
