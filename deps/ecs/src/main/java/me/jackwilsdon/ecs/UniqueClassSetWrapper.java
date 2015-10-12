package me.jackwilsdon.ecs;

import java.util.Set;

public class UniqueClassSetWrapper<E> extends UniqueSetWrapper<E> {
    public UniqueClassSetWrapper(Set<E> s) {
        super(s);
    }

    @Override
    protected Object getUniqueIdentifier(E e) {
        return e.getClass();
    }
}
