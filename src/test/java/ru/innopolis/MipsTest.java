package ru.innopolis;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MipsTest {

    private Mips<Integer, String> cosh = new Mips<>();
    private Map<Integer, String> map = new HashMap<>();

    void putGetRemove() {
        assertEquals(map.put(1, "1"), cosh.put(1, "1"));
        assertEquals(map.get(1), cosh.get(1));
        assertEquals(map.size(), cosh.size());

        assertEquals(map.put(1, null), cosh.put(1, null));
        assertEquals(map.get(1), cosh.get(1));
        assertEquals(map.size(), cosh.size());

        assertEquals(map.get(null), cosh.get(null));
        assertEquals(map.put(null, "NNN"), cosh.put(null, "NNN"));
        assertEquals(map.get(null), cosh.get(null));
        assertEquals(map.size(), cosh.size());
        assertEquals(map.put(null, null), cosh.put(null, null));
        assertEquals(map.get(null), cosh.get(null));
        assertEquals(map.size(), cosh.size());

        Double rand;
        ;
        for (int i = 2; i < 500; i++) {
            rand = Math.random();
            assertEquals(map.put(i, rand.toString()), cosh.put(i, rand.toString()));
        }
        assertEquals(map.size(), cosh.size());
        for (int i = 2; i < 500; i++) {
            assertEquals(map.get(i), cosh.get(i));
        }
    }

    @Test
    void size() {
        assertEquals(map.size(), cosh.size());
        assertEquals(map.put(1, "1"), cosh.put(1, "1"));
        assertEquals(map.size(), cosh.size());
        assertEquals(1, cosh.size());
        assertEquals(map.remove(1), cosh.remove(1));
        assertEquals(map.size(), cosh.size());
        assertEquals(0, cosh.size());
    }

    @Test
    void isEmpty() {
        assertEquals(map.isEmpty(), cosh.isEmpty());
        assertEquals(true, cosh.isEmpty());
        assertEquals(map.put(1, "1"), cosh.put(1, "1"));
        assertEquals(map.isEmpty(), cosh.isEmpty());
        assertEquals(false, cosh.isEmpty());
        assertEquals(map.remove(1), cosh.remove(1));
        assertEquals(map.isEmpty(), cosh.isEmpty());
    }

    @Test
    void containsKey() {
        assertEquals(map.containsKey(null), cosh.containsKey(null));
        assertEquals(false, cosh.containsKey(null));
        assertEquals(map.containsKey(100500), cosh.containsKey(100500));
        assertEquals(false, cosh.containsKey(100500));
        assertEquals(map.put(null, null), cosh.put(null, null));
        assertEquals(map.containsKey(null), cosh.containsKey(null));
        assertEquals(true, cosh.containsKey(null));
        assertEquals(map.remove(null), cosh.remove(null));
        assertEquals(map.containsKey(null), cosh.containsKey(null));
        assertEquals(false, cosh.containsKey(null));
    }

    @Test
    void containsValue() {

    }

    @Test
    void putAll() {

    }

    @Test
    void clear() {


        assertEquals(map.put(1, "1"), cosh.put(1, "1"));
        assertEquals(map.get(1), cosh.get(1));
        assertEquals(map.size(), cosh.size());



//        assertEquals(map.put(1, "1"), cosh.put(1, "1"));
//        assertEquals(map.get(1), cosh.get(1));
//        assertEquals(map.size(), cosh.size());
//        map.clear();
//        cosh.clear();
//        assertEquals(map.get(1), cosh.get(1));
//        assertEquals(map.size(), cosh.size());
//        assertEquals(0, cosh.size());
    }

    @Test
    void keySet() {

    }

    @Test
    void Collection() {

    }

    @Test
    void Set() {

    }
}