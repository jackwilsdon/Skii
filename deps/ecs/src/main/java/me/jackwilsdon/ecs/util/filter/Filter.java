package me.jackwilsdon.ecs.util.filter;

public interface Filter<E, T extends FilterContext> {
    boolean accept(E value, T context);
}
