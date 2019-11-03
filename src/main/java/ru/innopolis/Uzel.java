package ru.innopolis;

public class Uzel<K, V> {

    private K key;
    private V value;
    private Uzel next;

    public Uzel() {
    }

    public Uzel(K key, V value, Uzel next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public Uzel getNext() {
        return next;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(Uzel next) {
        this.next = next;
    }

}
