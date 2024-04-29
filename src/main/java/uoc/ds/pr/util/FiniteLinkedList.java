package uoc.ds.pr.util;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.FiniteContainer;

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

    // Sobreescribimos este método para comprobar si FiniteLinkedList está lleno y no permitir añadir más elementos
    @Override
    public Position<E> insertEnd(E elem) {
        if (!isFull())
            last = newPosition(last, elem);

        return last;
    }
}
