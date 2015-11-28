package me.jackwilsdon.ecs.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FilteredSetWrapper<E> implements Set<E> {
    private Set<E> set;
    private Filter<E> filter;

    private Set<E> filteredSet = new HashSet<>();

    public FilteredSetWrapper(Set<E> set, Filter<E> filter) {
        this.set = set;
        this.filter = filter;
    }

    private void updateFilteredSet() {
        filteredSet.clear();

        for (E element : set) {
            if (filter.accept(element)) {
                filteredSet.add(element);
            }
        }
    }

    @Override
    public int size() {
        updateFilteredSet();

        return filteredSet.size();
    }

    @Override
    public boolean isEmpty() {
        updateFilteredSet();

        return filteredSet.isEmpty();
    }

    @Override
    public boolean contains(Object value) {
        updateFilteredSet();

        return filteredSet.contains(value);
    }

    @Override
    public Iterator<E> iterator() {
        updateFilteredSet();

        return filteredSet.iterator();
    }

    @Override
    public Object[] toArray() {
        updateFilteredSet();

        return filteredSet.toArray();
    }

    @Override
    public <T> T[] toArray(T[] array) {
        updateFilteredSet();

        return filteredSet.toArray(array);
    }

    @Override
    public boolean add(E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        updateFilteredSet();

        return filteredSet.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
