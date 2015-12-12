package me.jackwilsdon.ecs.core.subsystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubSystemManager {
    private List<SubSystem> subSystems = new ArrayList<>();

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
        return !hasSubSystem(subSystem) && subSystems.add(subSystem);
    }

    public boolean removeSubSystem(Class<? extends SubSystem> subSystemClass) {
        Iterator<SubSystem> subSystemIterator = subSystems.iterator();

        while (subSystemIterator.hasNext()) {
            SubSystem subSystem = subSystemIterator.next();

            if (subSystem.getClass() == subSystemClass) {
                subSystemIterator.remove();
                return true;
            }
        }

        return false;
    }

    public boolean removeSubSystem(SubSystem subSystem) {
        return removeSubSystem(subSystem.getClass());
    }
}
