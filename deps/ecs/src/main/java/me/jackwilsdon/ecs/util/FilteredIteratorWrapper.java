package me.jackwilsdon.ecs.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilteredIteratorWrapper<E> implements Iterator<E> {
    private Iterator<E> iterator;
    private Filter<E> filter;

    private E iteratorNext;
    private boolean iteratorHasNext;

    public FilteredIteratorWrapper(Iterator<E> iterator, Filter<E> filter) {
        this.iterator = iterator;
        this.filter = filter;

        findNext();
    }

    private void findNext() {
        iteratorHasNext = false;

        while (iterator.hasNext()) {
            iteratorNext = iterator.next();

            if (filter.accept(iteratorNext)) {
                iteratorHasNext = true;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return iteratorHasNext;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        E nextValue = iteratorNext;
        findNext();
        return nextValue;
    }
}
