package me.jackwilsdon.ecs.util;

public class Utility {
    public static String getSimpleClassName(Class<?> clazz) {
        String name = clazz.getSimpleName();

        if (name.isEmpty()) {
            name = clazz.getName();
        }

        return name;
    }

    public static String pluralize(int count, String singular, String plural) {
        return count == 1 ? singular : plural;
    }
}
