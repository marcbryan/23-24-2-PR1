package uoc.ds.pr.util;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Traversal;

public class DSLinkedList<E> extends LinkedList<E> {
    /**
     * Método para obtener la posición de un elemento en la lista encadenada
     * @param elem El elemento que se buscará en la lista encadenada
     * @return La posición del elemento en la lista encadenada (puede ser null si no se encuentra)
     * **/
    public Position<E> getElementPosition(E elem) {
        Traversal<E> positions = positions();

        while(positions().hasNext()) {
            LinkedNode<E> pos = (LinkedNode<E>) positions.next();
            if (pos.getElem() == elem)
                return pos;
        }

        return null;
    }
}
