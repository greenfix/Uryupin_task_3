package ru.innopolis;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Mips<K, V> implements Map<K, V> {

    private static final float FACTOR = 2.0f;
    private static final int CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private float loadFactor;
    private int capacity;
    private int size = 0;
    private Uzel[] kase;

    /**
     *
     */
    public Mips() {
        capacity = CAPACITY;
        loadFactor = LOAD_FACTOR;
        kase = new Uzel[capacity];
    }

    /**
     * @param capacity
     */
    public Mips(int capacity) {
        this.capacity = capacity;
        loadFactor = LOAD_FACTOR;
        kase = new Uzel[capacity];
    }

    /**
     * @param capacity   capacity
     * @param LoadFactor LoadFactor
     */
    public Mips(int capacity, float LoadFactor) {
        this.capacity = capacity;
        this.loadFactor = LoadFactor;
        kase = new Uzel[capacity];
    }

    @Override
    public V put(K key, V value) {
        if ((1f * size / capacity) >= loadFactor) {
            expansionKase();
        }

        return putService(key, value, kase);
    }

    @Override
    public V get(Object K) {
        return null;
    }

    @Override
    public V remove(Object key) {
        int basket = getIndexBasket(key);
        Uzel uzel = kase[basket];
        if (uzel == null) {

            return null;
        }
        int level = 0;
        Uzel prevUzel = uzel;
        while (true) {
            if (isNeedUzel(key, uzel)) {
                break;
            }
            level++;
            prevUzel = uzel;
            uzel = uzel.getNext();
            if (uzel == null) {

                return null;
            }
        }
        Object oldValue = uzel.getValue();
        if (level == 0) {
            kase[basket] = uzel.getNext();
        } else {
            prevUzel.setNext(uzel.getNext());
        }
        size--;

        return (V) oldValue;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {

        return (size == 0);
    }

    @Override
    public boolean containsKey(Object key) {
        int basket = getIndexBasket(key);
        Uzel uzel = kase[basket];
        if (uzel == null) {

            return false;
        }
        while (true) {
            if (isNeedUzel(key, uzel)) {
                return true;
            }
            uzel = uzel.getNext();
            if (uzel == null) {

                return false;
            }
        }
    }

    @Override
    public boolean containsValue(Object value) {

        return false;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {
//        capacity = CAPACITY;
//        loadFactor = LOAD_FACTOR;
//        kase = new Uzel[capacity];
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    /**
     * @param K     key
     * @param V     value
     * @param nodes nodes
     * @return Object
     */
    private V putService(K K, V V, Uzel[] nodes) {
        int basket = getIndexBasket(K);
        Uzel uzel = nodes[basket];
        if (uzel == null) {
            // first in basket - simple add
            nodes[basket] = new Uzel(K, V, null);
            size++;
        } else {
            while (true) {
                if (isNeedUzel(K, uzel)) {
                    // rewrite
                    Object oldValue = uzel.getValue();
                    uzel.setValue(V);
                    return (V) oldValue;
                }
                if (uzel.getNext() == null) {
                    // last in basket - add to end
                    uzel.setNext(new Uzel(K, V, null));
                    size++;
                    break;
                }
                uzel = uzel.getNext();
            }
        }

        return null;
    }

    /**
     * @param K key
     * @return index of basket
     */
    private int getIndexBasket(Object K) {
        if (K == null) {
            return 0;
        }
        int h = K.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        h = h ^ (h >>> 7) ^ (h >>> 4);

        return (h & (capacity - 1));
    }

    /**
     *
     */
    private void expansionKase() {
        capacity = (int) (capacity * FACTOR);
        Uzel[] expansion = new Uzel[capacity];
        Uzel uzel;
        size = 0;
        for (int i = 0; i < kase.length; i++) {
            uzel = kase[i];
            if (uzel != null) {
                do {
                    putService((K) uzel.getKey(), (V) uzel.getValue(), expansion);
                    uzel = uzel.getNext();
                } while (uzel != null);
            }
        }
        kase = expansion;
    }

    /**
     * @param K    key
     * @param uzel uzel
     * @return true if needle
     */
    private boolean isNeedUzel(Object K, Uzel uzel) {
        Object keyUzel = uzel.getKey();
        return ((keyUzel == null && K == null) ||
                (K != null && K.equals(keyUzel)) ||
                (keyUzel != null && keyUzel.equals(K)));
    }
}
