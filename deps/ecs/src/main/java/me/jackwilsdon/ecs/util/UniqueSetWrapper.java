package me.jackwilsdon.ecs.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class UniqueSetWrapper<E> implements Set<E> {
    Set<E> set;

    public UniqueSetWrapper(Set<E> set) {
        this.set = set;
    }

    private boolean containsIdentifier(Object identifier) {
        for (E element : set) {
            if (getUniqueIdentifier(element) == identifier) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean add(E element) {
        Object identifier = getUniqueIdentifier(element);

        if (containsIdentifier(identifier)) {
            return false;
        }

        return set.add(element);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        boolean changed = false;

        for (E element : collection) {
            if (add(element)) {
                changed = true;
            }
        }

        return changed;
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public boolean contains(Object value) {
        return set.contains(value);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return set.containsAll(collection);
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public boolean remove(Object value) {
        return set.remove(value);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return set.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return set.retainAll(collection);
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] array) {
        return set.toArray(array);
    }

    public E get(Object identifier) {
        for (E element : set) {
            if (getUniqueIdentifier(element) == identifier) {
                return element;
            }
        }

        return null;
    }

    protected abstract Object getUniqueIdentifier(E element);
}
