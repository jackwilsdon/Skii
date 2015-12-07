package me.jackwilsdon.ecs.util;

import me.jackwilsdon.ecs.core.MessageListener;

import java.lang.reflect.Method;
import java.util.Comparator;

public class MessageListenerComparator implements Comparator<Method> {

    private int getPriority(Method method) {
        MessageListener annotation = method.getAnnotation(MessageListener.class);

        return annotation == null ? 0 : annotation.priority();
    }

    @Override
    public int compare(Method o1, Method o2) {
        int p1 = getPriority(o1);
        int p2 = getPriority(o2);

        if (p1 < p2) {
            return -1;
        } else if (p1 > p2) {
            return 1;
        }

        return 0;
    }
}
