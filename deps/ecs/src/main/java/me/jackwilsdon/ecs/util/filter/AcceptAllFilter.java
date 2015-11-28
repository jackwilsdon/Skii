package me.jackwilsdon.ecs.util.filter;

public class AcceptAllFilter<T> implements Filter<T> {

    @Override
    public boolean accept(T value) {
        return true;
    }
}
