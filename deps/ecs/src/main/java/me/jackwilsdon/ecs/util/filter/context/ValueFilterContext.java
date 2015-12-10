package me.jackwilsdon.ecs.util.filter.context;

public class ValueFilterContext<T> implements FilterContext {
    public T value;

    public ValueFilterContext(T value) {
        this.value = value;
    }

    public ValueFilterContext() {
        this(null);
    }
}
