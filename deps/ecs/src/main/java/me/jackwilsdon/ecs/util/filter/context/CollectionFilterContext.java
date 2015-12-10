package me.jackwilsdon.ecs.util.filter.context;

import java.util.Collection;

public class CollectionFilterContext<E> extends ValueFilterContext<Collection<E>> {
    public CollectionFilterContext(Collection<E> value) {
        super(value);
    }

    public CollectionFilterContext() {
        super();
    }
}
