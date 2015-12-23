package me.jackwilsdon.ecs.core.component;

public interface Component<T extends Component> {
    T copy();
}
