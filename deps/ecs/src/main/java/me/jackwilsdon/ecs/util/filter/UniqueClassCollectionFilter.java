package me.jackwilsdon.ecs.util.filter;

public class UniqueClassCollectionFilter<E> extends UniqueCollectionFilter<E> {

    @Override
    public Object getIdentifier(E value) {
        return value.getClass();
    }
}
