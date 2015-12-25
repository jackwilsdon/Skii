package me.jackwilsdon.ecs.subsystem;

import me.jackwilsdon.ecs.Engine;
import me.jackwilsdon.ecs.core.SubSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubSystemManager {
    private List<SubSystem> subSystems = new ArrayList<>();
    private Engine engine;

    public SubSystemManager(Engine engine) {
        this.engine = engine;
    }

    public boolean hasSubSystem(Class<? extends SubSystem> subSystemClass) {
        for (SubSystem subSystem : getSubSystems()) {
            if (subSystem.getClass() == subSystemClass) {
                return true;
            }
        }

        return false;
    }

    public boolean hasSubSystem(SubSystem subSystem) {
        return hasSubSystem(subSystem.getClass());
    }

    public SubSystem[] getSubSystems() {
        return subSystems.toArray(new SubSystem[subSystems.size()]);
    }

    public <T extends SubSystem> T getSubSystem(Class<T> subSystemClass) {
        for (SubSystem subSystem : getSubSystems()) {
            if (subSystem.getClass() == subSystemClass) {
                return subSystemClass.cast(subSystem);
            }
        }

        return null;
    }

    public boolean addSubSystem(SubSystem subSystem) {
        if (!hasSubSystem(subSystem) && subSystems.add(subSystem)) {
            subSystem.onAddedToEngine(engine);

            return true;
        }

        return false;
    }

    public boolean removeSubSystem(Class<? extends SubSystem> subSystemClass) {
        Iterator<SubSystem> subSystemIterator = subSystems.iterator();
        List<SubSystem> removedSubSystems = new ArrayList<>();

        while (subSystemIterator.hasNext()) {
            SubSystem subSystem = subSystemIterator.next();

            if (subSystem.getClass() == subSystemClass) {
                subSystemIterator.remove();
                removedSubSystems.add(subSystem);
                break;
            }
        }

        for (SubSystem subSystem : removedSubSystems) {
            subSystem.onRemovedFromEngine(engine);
        }

        return false;
    }

    public boolean removeSubSystem(SubSystem subSystem) {
        return removeSubSystem(subSystem.getClass());
    }
}
