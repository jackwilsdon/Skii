package me.jackwilsdon.ecs.core.subsystem;

import me.jackwilsdon.ecs.core.Engine;

public class EngineSubSystemManager extends SubSystemManager {
    private Engine engine;

    public EngineSubSystemManager(Engine engine) {
        this.engine = engine;
    }

    @Override
    public boolean addSubSystem(SubSystem subSystem) {
        if (super.addSubSystem(subSystem)) {
            subSystem.onAddedToEngine(engine);

            return true;
        }

        return false;
    }

    @Override
    public boolean removeSubSystem(Class<? extends SubSystem> subSystemClass) {
        SubSystem subSystem = getSubSystem(subSystemClass);

        if (super.removeSubSystem(subSystemClass)) {
            subSystem.onRemovedFromEngine(engine);

            return true;
        }

        return false;
    }
}
