package me.jackwilsdon.ecs.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PropertyManager {
    private List<Property> properties = new ArrayList<>();

    public boolean hasProperty(Class<? extends Property> propertyClass) {
        for (Property property : getProperties()) {
            if (property.getClass() == propertyClass) {
                return true;
            }
        }

        return false;
    }

    public boolean hasProperty(Property property) {
        return hasProperty(property.getClass());
    }

    public Property[] getProperties() {
        return properties.toArray(new Property[properties.size()]);
    }

    public <T extends Property> T getProperty(Class<T> propertyClass) {
        for (Property property : getProperties()) {
            if (property.getClass() == propertyClass) {
                return propertyClass.cast(property);
            }
        }

        return null;
    }

    public boolean addProperty(Property property) {
        return !hasProperty(property) && properties.add(property);

    }

    public boolean removeProperty(Class<? extends Property> propertyClass) {
        Iterator<Property> propertyIterator = properties.iterator();

        while (propertyIterator.hasNext()) {
            Property property = propertyIterator.next();

            if (property.getClass() == propertyClass) {
                propertyIterator.remove();
                return true;
            }
        }

        return false;
    }

    public boolean removeProperty(Property property) {
        return removeProperty(property.getClass());
    }
}
