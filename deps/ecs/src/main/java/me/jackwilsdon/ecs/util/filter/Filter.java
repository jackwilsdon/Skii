package me.jackwilsdon.ecs.util.filter;

public interface Filter<T> {
    boolean accept(T value);
}
