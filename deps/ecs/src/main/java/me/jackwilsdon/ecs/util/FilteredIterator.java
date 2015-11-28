package me.jackwilsdon.ecs.util;

import java.util.Iterator;

public class FilteredIterator<E> implements Iterator<E> {
    private Iterator<E> iterator;
    private Filter<E> filter;

    private E iteratorNext;
    private boolean iteratorHasNext;

    public FilteredIterator(Iterator<E> iterator, Filter<E> filter) {
        this.iterator = iterator;
        this.filter = filter;

        findNextValid();
    }

    private void findNextValid() {
        iteratorHasNext = iterator.hasNext();

        while (iterator.hasNext()) {
            iteratorNext = iterator.next();

            if (filter == null || filter.apply(iteratorNext)) {
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
        E nextValue = iteratorNext;
        findNextValid();
        return nextValue;
    }

    @Override
    public void remove() {
        iterator.remove();
    }
}
