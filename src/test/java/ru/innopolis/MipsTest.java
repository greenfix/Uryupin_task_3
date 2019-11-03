package ru.innopolis;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MipsTest {

    private Mips<Integer, String> mips = new Mips<>();
    private Map<Integer, String> map = new HashMap<>();

    @Test
    void putGetRemove() {
        assertEquals(map.put(1, "1"), mips.put(1, "1"));
        assertEquals(map.get(1), mips.get(1));
        assertEquals(map.size(), mips.size());

        assertEquals(map.put(1, null), mips.put(1, null));
        assertEquals(map.get(1), mips.get(1));
        assertEquals(map.size(), mips.size());

        assertEquals(map.get(null), mips.get(null));
        assertEquals(map.put(null, "NNN"), mips.put(null, "NNN"));
        assertEquals(map.get(null), mips.get(null));
        assertEquals(map.size(), mips.size());
        assertEquals(map.put(null, null), mips.put(null, null));
        assertEquals(map.get(null), mips.get(null));
        assertEquals(map.size(), mips.size());

        Double rand;
        ;
        for (int i = 2; i < 500; i++) {
            rand = Math.random();
            assertEquals(map.put(i, rand.toString()), mips.put(i, rand.toString()));
        }
        assertEquals(map.size(), mips.size());
        for (int i = 2; i < 500; i++) {
            assertEquals(map.get(i), mips.get(i));
        }
    }

    @Test
    void size() {
        assertEquals(map.size(), mips.size());
        assertEquals(map.put(1, "1"), mips.put(1, "1"));
        assertEquals(map.size(), mips.size());
        assertEquals(1, mips.size());
        assertEquals(map.remove(1), mips.remove(1));
        assertEquals(map.size(), mips.size());
        assertEquals(0, mips.size());
    }

    @Test
    void isEmpty() {
        assertEquals(map.isEmpty(), mips.isEmpty());
        assertEquals(true, mips.isEmpty());
        assertEquals(map.put(1, "1"), mips.put(1, "1"));
        assertEquals(map.isEmpty(), mips.isEmpty());
        assertEquals(false, mips.isEmpty());
        assertEquals(map.remove(1), mips.remove(1));
        assertEquals(map.isEmpty(), mips.isEmpty());
    }

    @Test
    void containsKey() {
        assertEquals(map.containsKey(null), mips.containsKey(null));
        assertEquals(false, mips.containsKey(null));
        assertEquals(map.containsKey(100500), mips.containsKey(100500));
        assertEquals(false, mips.containsKey(100500));
        assertEquals(map.put(null, null), mips.put(null, null));
        assertEquals(map.containsKey(null), mips.containsKey(null));
        assertEquals(true, mips.containsKey(null));
        assertEquals(map.remove(null), mips.remove(null));
        assertEquals(map.containsKey(null), mips.containsKey(null));
        assertEquals(false, mips.containsKey(null));
    }

    @Test
    void containsValue() {
        assertEquals(map.containsValue(null), mips.containsValue(null));
        assertEquals(map.containsValue(100500), mips.containsValue(10050));
        assertEquals(map.put(null, null), mips.put(null, null));
        assertEquals(map.containsValue(null), mips.containsValue(null));
        assertEquals(map.put(1, "null"), mips.put(1, "null"));
        assertEquals(map.containsValue("null"), mips.containsValue("null"));
    }

    @Test
    void putAll() {
        Map<Integer, String> scr = new HashMap<>();
        scr.put(1, "1");
        scr.put(2, "2");
        scr.put(null, "null");
        scr.put(3, null);
        assertEquals(map.put(0, "zero"), mips.put(0, "zero"));
        map.putAll(scr);
        mips.putAll(scr);
        assertEquals(map.size(), mips.size());
        assertEquals(map.get(1), mips.get(1));
        assertEquals(map.get(2), mips.get(2));
        assertEquals(map.get(3), mips.get(3));
        assertEquals(map.get(null), mips.get(null));
    }

    @Test
    void clear() {
        assertEquals(map.put(1, "1"), mips.put(1, "1"));
        assertEquals(map.get(1), mips.get(1));
        assertEquals(map.size(), mips.size());
        map.clear();
        mips.clear();
        assertEquals(map.get(1), mips.get(1));
        assertEquals(map.size(), mips.size());
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