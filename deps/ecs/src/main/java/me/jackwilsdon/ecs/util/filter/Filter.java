package me.jackwilsdon.ecs.util.filter;

import me.jackwilsdon.ecs.util.filter.context.FilterContext;

public interface Filter<E, T extends FilterContext> {
    boolean accept(E value, T context);
}
