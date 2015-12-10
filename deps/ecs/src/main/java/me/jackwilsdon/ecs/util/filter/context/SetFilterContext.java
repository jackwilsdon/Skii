package me.jackwilsdon.ecs.util.filter.context;

import java.util.Set;

public class SetFilterContext<E> extends ValueFilterContext<Set<E>> {
    public SetFilterContext(Set<E> value) {
        super(value);
    }

    public SetFilterContext() {
        super();
    }
}
