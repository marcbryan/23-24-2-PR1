package uoc.ds.pr.util;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.FiniteContainer;
import edu.uoc.ds.traversal.Iterator;

public class FiniteLinkedList<E> extends LinkedList<E> implements FiniteContainer<E> {
    private int length;

    public FiniteLinkedList(int length) {
        this.length = length;
    }

    public int length() {
        return length;
    }

    @Override
    public boolean isFull() {
        return (n == length);
    }
}
