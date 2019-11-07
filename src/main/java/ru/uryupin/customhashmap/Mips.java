package ru.uryupin.customhashmap;

import java.util.*;

public class Mips<K, V> implements Map<K, V> {

    private static final int CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private static final float FACTOR = 2.0f;

    private float loadFactor;
    private int capacity;
    private int size = 0;
    public Uzel[] kase;

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
        Uzel uzel = kase[getIndexBasket(K)];
        if (uzel == null) {

            return null;
        }
        while (true) {
            if (isNeedUzel(K, uzel)) {
                break;
            }
            uzel = uzel.getNext();
            if (uzel == null) {
                return null;
            }
        }

        return (V) uzel.getValue();
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
        Uzel uzel;
        Object uzelValue;
        for (int i = 0; i < kase.length; i++) {
            uzel = kase[i];
            if (uzel != null) {
                do {
                    uzelValue = uzel.getValue();
                    if ((uzelValue == null && value == null) ||
                            (uzelValue != null && uzelValue.equals(value)) ||
                            (value != null && value.equals(uzelValue))) {
                        return true;
                    }
                    uzel = uzel.getNext();
                } while (uzel != null);
            }
        }

        return false;
    }

    @Override
    public void putAll(Map m) {
        Iterator<Entry<Integer, String>> it = m.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> pair = it.next();
            put((K) pair.getKey(), (V) pair.getValue());
        }
    }

    @Override
    public void clear() {
        capacity = CAPACITY;
        loadFactor = LOAD_FACTOR;
        size = 0;
        kase = new Uzel[capacity];
    }

    @Override
    public Set keySet() {
        HashSet<K> resultSet = new HashSet<K>();
        Uzel uzel;
        for (int i = 0; i < kase.length; i++) {
            uzel = kase[i];
            if (uzel != null) {
                do {
                    resultSet.add((K) uzel.getKey());
                    uzel = uzel.getNext();
                } while (uzel != null);
            }
        }

        return resultSet;
    }

    @Override
    public Collection values() {
        List<V> resultValues = new ArrayList<>();
        Uzel uzel;
        for (int i = 0; i < kase.length; i++) {
            uzel = kase[i];
            if (uzel != null) {
                do {
                    resultValues.add((V) uzel.getValue());
                    uzel = uzel.getNext();
                } while (uzel != null);
            }
        }

        return resultValues;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> resultSet = new HashSet<>();
        Entry<K, V> entry;
        Uzel uzel;
        for (int i = 0; i < kase.length; i++) {
            uzel = kase[i];
            if (uzel != null) {
                do {
                    entry = new AbstractMap.SimpleEntry((K) uzel.getKey(), (V) uzel.getValue());
                    resultSet.add(entry);
                    uzel = uzel.getNext();
                } while (uzel != null);
            }
        }

        return resultSet;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Mips))
            return false;
        Mips<?,?> m = (Mips<?,?>) o;
        if (m.size() != size())
            return false;

        try {
            Iterator<Entry<K,V>> i = entrySet().iterator();
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                K key = e.getKey();
                V value = e.getValue();
                if (value == null) {
                    if (!(m.get(key)==null && m.containsKey(key)))
                        return false;
                } else {
                    if (!value.equals(m.get(key)))
                        return false;
                }
            }
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int h = 0;
        Iterator<Entry<K,V>> i = entrySet().iterator();
        while (i.hasNext())
            h += i.next().hashCode();
        return h;
    }

    /**
     *
     */
    public void showStructure() {
        Uzel uzel;
        for (int i = 0; i < kase.length; i++) {
            System.out.print(i + ": ");
            uzel = kase[i];
            if (uzel != null) {
                do {
                    System.out.print("* ");
                    uzel = uzel.getNext();
                } while (uzel != null);
            }
            System.out.println("");
        }
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
