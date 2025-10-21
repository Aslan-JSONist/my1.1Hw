package com.mipt.aslanfarajov.CollectionsClasses;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com.mipt.aslanfarajov.CollectionsClasses.CustomList;
import com.mipt.aslanfarajov.CollectionsClasses.CustomArrayList;

public class CustomArrayList<A> implements CustomList<A> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public CustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative");
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    @Override
    public void add(A element) {
        if (element == null) {
            throw new IllegalArgumentException("Null elements are not allowed");
        }

        ensureCapacity();
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public A get(int index) {
        checkIndex(index);
        return (A) elements[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public A remove(int index) {
        checkIndex(index);

        A removedElement = (A) elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null;
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<A> iterator() {
        return new CustomIterator();
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * 1.5) + 1;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private class CustomIterator implements Iterator<A> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public A next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (A) elements[currentIndex++];
        }
    }
}