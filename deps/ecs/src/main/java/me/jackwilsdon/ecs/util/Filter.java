    package me.jackwilsdon.ecs.util;

    public interface Filter<T> {
        boolean accept(T type);
    }
