package me.jackwilsdon.ecs;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class UniqueSetWrapper<E> implements Set<E> {
    Set<E> set;

    public UniqueSetWrapper(Set<E> s) {
        set = s;
    }

    private boolean containsIdentifier(Object o) {
        for (E element : set) {
            if (getUniqueIdentifier(element) == o) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean add(E e) {
        Object identifier = getUniqueIdentifier(e);

        if (containsIdentifier(identifier)) {
            return false;
        }

        return set.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;

        for (E element : c) {
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
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
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
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
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
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    public E get(Object o) {
        for (E element: set) {
            if (getUniqueIdentifier(element) == o) {
                return element;
            }
        }

        return null;
    }

    protected abstract Object getUniqueIdentifier(E e);
}
