package me.jackwilsdon.ecs.util.filter;

import me.jackwilsdon.ecs.util.filter.context.FilterContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CompoundFilter<E, T extends FilterContext> implements Filter<E, T> {
    private Set<Filter<E, T>> filters = new HashSet<>();

    @SafeVarargs
    public CompoundFilter(Filter<E, T>... filters) {
        Collections.addAll(this.filters, filters);
    }

    @Override
    public boolean accept(E value, T context) {
        for (Filter<E, T> filter : filters) {
            if (!filter.accept(value, context)) {
                return false;
            }
        }

        return true;
    }
}
