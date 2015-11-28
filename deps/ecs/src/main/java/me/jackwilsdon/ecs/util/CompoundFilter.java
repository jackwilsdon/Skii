package me.jackwilsdon.ecs.util;

import me.jackwilsdon.ecs.core.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CompoundFilter<T> implements Filter<T> {
    private Set<Filter<T>> filters = new HashSet<>();

    @SafeVarargs
    public CompoundFilter(Filter<T>... filters) {
        Collections.addAll(this.filters, filters);
    }

    @Override
    public boolean accept(T value) {
        for (Filter<T> filter : filters) {
            if (!filter.accept(value)) {
                return false;
            }
        }

        return true;
    }
}
