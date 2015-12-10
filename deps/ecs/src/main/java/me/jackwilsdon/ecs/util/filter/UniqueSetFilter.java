package me.jackwilsdon.ecs.util.filter;

import me.jackwilsdon.ecs.util.filter.context.SetFilterContext;

public abstract class UniqueSetFilter<E> implements SetFilter<E> {

    @Override
    public final boolean accept(E value, SetFilterContext<E> context) {
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
