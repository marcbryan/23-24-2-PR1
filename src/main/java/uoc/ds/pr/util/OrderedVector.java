package uoc.ds.pr.util;

import edu.uoc.ds.adt.sequential.FiniteContainer;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.IteratorArrayImpl;

import java.util.Comparator;

public class OrderedVector<E> implements FiniteContainer<E> {
    private int length;
    private Comparator<Integer> comparator;
    private E[] elements;

    public OrderedVector(int maxLength, Comparator<Integer> comparator) {
        this.length = 0;
        this.comparator = comparator;
        this.elements = (E[]) new Object[maxLength];
    }

    @Override
    public boolean isFull() {
        return (this.length == this.elements.length);
    }

    @Override
    public boolean isEmpty() {
        return (this.length == 0);
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public Iterator<E> values() {
        return new IteratorArrayImpl<>(elements, length, 0);
    }

    // TODO: Implementar métodos
    public void update(E vIn) {

    }

    public void delete (E elem) {

    }
}
