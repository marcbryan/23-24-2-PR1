package uoc.ds.pr.util;

import edu.uoc.ds.adt.sequential.FiniteContainer;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.IteratorArrayImpl;

import java.util.Comparator;

public class OrderedVector<E> implements FiniteContainer<E> {
    private int length;
    private Comparator<E> comparator;
    private E[] elements;

    public OrderedVector(int maxLength, Comparator<E> comparator) {
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

    // Compara si el primer elemento es igual al segundo
    private boolean compare(E elem1, E elem2) {
        return ((Comparable<E>) elem1).compareTo(elem2) == 0;
    }

    public void rshift(int i) {
        // Desplaza todos los elementos una posición a la derecha
        int pos = length - 1;
        while (pos >= i) {
            elements[pos+1] = elements[pos];
            pos--;
        }
    }

    public void lshift(int i) {
        // Desplaza todos los elementos una posición a la izquierda
        int pos = i;
        while (pos < length) {
            elements[pos] = elements[pos+1];
            pos++;
        }
    }

    public E first() {
        return elements[0];
    }

    public E last() {
        return elements[length-1];
    }

    public void update(E vIn) {
        int i = 0;
        boolean end = false;
        E v = null;

        // Si existe el elemento lo borramos para volverlo a insertar en su posición
        this.delete(vIn);

        if (isFull()) {
            E pOut = last();
            if (comparator.compare(pOut, vIn)>0) {
                this.delete(pOut);
                this.update(vIn);
            }
        }

        // Buscamos la posición donde insertarlo
        while (i < length && elements[i] != null && comparator.compare(elements[i], vIn) >= 0)
            i++;

        // Movemos los elementos a la derecha
        rshift(i);

        // Añadimos el elemento en la posición
        elements[i] = vIn;
        length++;
    }

    public void delete (E elem) {
        int i = 0;
        boolean found = false;

        // Buscamos el elemento que queremos borrar
        while (!found && i < length)
            found = (compare(elem, elements[i++]));

        // Si lo encontramos, desplazamos a la izquierda los elementos
        if (found) {
            lshift(i-1);
            length--;
        }
    }
}
