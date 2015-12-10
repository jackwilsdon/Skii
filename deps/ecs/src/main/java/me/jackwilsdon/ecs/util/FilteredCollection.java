package me.jackwilsdon.ecs.util;

import me.jackwilsdon.ecs.util.filter.Filter;
import me.jackwilsdon.ecs.util.filter.context.CollectionFilterContext;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class FilteredCollection<E> implements Collection<E> {
    private Filter<E, CollectionFilterContext<E>> filter;
    private CollectionFilterContext<E> context;

    private Collection<E> collection;
    private Collection<E> readOnlyCollection;

    public FilteredCollection(Filter<E, CollectionFilterContext<E>> filter, Collection<E> collection) {
        this.filter = filter;
        this.context = new CollectionFilterContext<>(this);

        this.collection = collection;
        this.readOnlyCollection = Collections.unmodifiableCollection(collection);
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return collection.iterator();
    }

    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return filter.accept(e, context) && collection.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return collection.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;

        for (E e : c) {
            changed = add(e) || changed;
        }

        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return collection.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return collection.removeAll(c);
    }

    @Override
    public void clear() {
        collection.clear();
    }
}
