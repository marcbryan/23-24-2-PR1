package uoc.ds.pr.util;

import edu.uoc.ds.adt.nonlinear.Dictionary;
import edu.uoc.ds.adt.sequential.FiniteContainer;
import edu.uoc.ds.exceptions.FullContainerException;
import edu.uoc.ds.exceptions.InvalidPositionException;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.adt.helpers.KeyValue;
import edu.uoc.ds.traversal.IteratorArrayImpl;

public class DSArray<K, V> implements Dictionary<K, V>, FiniteContainer<V> {
    protected KeyValue<K, V>[] dictionary;
    protected int n;

    // Constructor
    public DSArray(int n) {
        dictionary =  new KeyValue[n];
    }

    @Override
    public void put(K key, V value) {
        // TODO: Revisar excepcion
        if (isFull()) throw new FullContainerException();
        int i;
        for (i = 0; i < n && !dictionary[i].getKey().equals(key); i++) ;

        dictionary[i] = new KeyValue<>(key, value);
        if (i == n)
            n++;
    }

    @Override
    public boolean containsKey(K key) {
        int i;
        for (i = 0; i < n && !dictionary[i].getKey().equals(key); i++) ;
        return i < n;
    }

    @Override
    public V get(K key) {
        int i;
        for (i = 0; i < n && !dictionary[i].getKey().equals(key); i++) ;
        return i < n ? dictionary[i].getValue() : null;
    }

    @Override
    public V delete(K key) {
        V elem = null;
        int i;
        for (i = 0; i < n && !dictionary[i].getKey().equals(key); i++) ;

        if (i < n) {
            elem = dictionary[i].getValue();
            dictionary[i] = dictionary[n - 1];
            dictionary[n - 1] = null;
            n--;
        }
        return elem;
    }

    @Override
    public boolean isFull() {
        return n == dictionary.length;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterator<K> keys() {
        return new KeysIterator<>(new IteratorArrayImpl<>(dictionary, n, 0));
    }

    @Override
    public Iterator<V> values() {
        return new ValuesIterator<>(new IteratorArrayImpl<>(dictionary, n, 0));
    }

    protected static class KeysIterator<K, V> implements Iterator<K> {

        private Iterator<KeyValue<K, V>> keyValueIterator;

        public KeysIterator(Iterator<KeyValue<K, V>> iterador) {
            keyValueIterator = iterador;
        }

        public boolean hasNext() {
            return keyValueIterator.hasNext();
        }

        public K next() {
            if (!hasNext())
                throw new InvalidPositionException();
            return keyValueIterator.next().getKey();
        }

    }


    protected static class ValuesIterator<K, V> implements Iterator<V> {

        private Iterator<KeyValue<K, V>> iteradorClauValor;

        public ValuesIterator(Iterator<KeyValue<K, V>> iterador) {
            iteradorClauValor = iterador;
        }

        public boolean hasNext() {
            return iteradorClauValor.hasNext();
        }

        public V next() {
            if (!hasNext())
                throw new InvalidPositionException();
            return iteradorClauValor.next().getValue();
        }
    }
}
