package me.jackwilsdon.ecs.util.filter;

public class AcceptAllFilter<E> implements Filter<E> {

    @Override
    public boolean accept(E value) {
        return true;
    }
}
