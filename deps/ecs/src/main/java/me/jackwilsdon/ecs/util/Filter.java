    package me.jackwilsdon.ecs.util;

    public interface Filter<T> {
        boolean apply(T type);
    }
