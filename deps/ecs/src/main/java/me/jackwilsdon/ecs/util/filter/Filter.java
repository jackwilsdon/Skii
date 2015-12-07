package me.jackwilsdon.ecs.util.filter;

public interface Filter<E> {
    boolean accept(E value);
}
