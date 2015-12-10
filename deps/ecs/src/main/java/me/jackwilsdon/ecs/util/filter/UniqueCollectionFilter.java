package me.jackwilsdon.ecs.util.filter;

import me.jackwilsdon.ecs.util.filter.context.CollectionFilterContext;

public abstract class UniqueCollectionFilter<E> implements CollectionFilter<E> {

    @Override
    public final boolean accept(E value, CollectionFilterContext<E> context) {
        Object identifier = getIdentifier(value);

        for (E setValue : context.value) {
            if (identifier == getIdentifier(setValue)) {
                return true;
            }
        }

        return false;
    }

    public abstract Object getIdentifier(E value);
}
