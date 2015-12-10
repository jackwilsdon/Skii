package me.jackwilsdon.ecs.util.filter;

public class UniqueClassSetFilter<E> extends UniqueSetFilter<E> {

    @Override
    public Object getIdentifier(E value) {
        return value.getClass();
    }
}
