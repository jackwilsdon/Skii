package me.jackwilsdon.ecs.util.filter;

public class AcceptAllFilter<E, T extends FilterContext> implements Filter<E, T> {

    @Override
    public boolean accept(E value, T context) {
        return true;
    }
}
