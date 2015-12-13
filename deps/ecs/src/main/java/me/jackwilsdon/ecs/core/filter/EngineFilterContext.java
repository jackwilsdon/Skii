package me.jackwilsdon.ecs.core.filter;

import me.jackwilsdon.ecs.core.Engine;
import me.jackwilsdon.ecs.util.filter.FilterContext;

public class EngineFilterContext implements FilterContext {
    public Engine engine;

    public EngineFilterContext(Engine engine) {
        this.engine = engine;
    }

    public EngineFilterContext() {
        this(null);
    }
}
