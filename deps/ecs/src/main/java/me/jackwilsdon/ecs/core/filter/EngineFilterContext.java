package me.jackwilsdon.ecs.core.filter;

import me.jackwilsdon.ecs.core.Engine;
import me.jackwilsdon.ecs.util.filter.FilterContext;

public class EngineFilterContext implements FilterContext {
    private Engine engine;

    public EngineFilterContext(Engine engine) {
        this.engine = engine;
    }

    public EngineFilterContext() {
        this(null);
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }
}
