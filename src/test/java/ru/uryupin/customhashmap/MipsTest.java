package ru.uryupin.customhashmap;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MipsTest {

    private Mips<Integer, String> mips = new Mips<>();
    private Map<Integer, String> map = new HashMap<>();

    @Test
    void putGetRemoveTest() {

        // positive tests

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

//       mips.showStructure();

        Double rand;
        int maxItems = 105;
        for (Integer i = 2; i < maxItems; i++) {
            rand = Math.random();
            assertEquals(map.put(i, rand.toString()), mips.put(i, rand.toString()));
        }

        // add collisions
        assertEquals(map.put(272, "272+"), mips.put(272, "272+"));
        assertEquals(map.put(273, "273+"), mips.put(273, "273+"));
        assertEquals(map.put(274, "274+"), mips.put(274, "274+"));
        assertEquals(map.put(275, "275+"), mips.put(275, "275+"));

        assertEquals(map.size(), mips.size());
        for (int i = 2; i < maxItems; i++) {
            assertEquals(map.get(i), mips.get(i));
        }

        // test collisions
        assertEquals(map.get(272), mips.get(272));
        assertEquals(map.get(273), mips.get(273));
        assertEquals(map.get(274), mips.get(274));
        assertEquals(map.get(275), mips.get(275));

//        mips.showStructure();

        // negative tests

        assertNotEquals(map.put(272, "1"), mips.put(273, "2"));
        assertNotEquals(map.get(272), mips.get(273));

    }

    @Test
    void sizeTest() {
        assertEquals(map.size(), mips.size());
        assertEquals(map.put(1, "1"), mips.put(1, "1"));
        assertEquals(map.size(), mips.size());
        assertEquals(1, mips.size());
        assertEquals(map.remove(1), mips.remove(1));
        assertEquals(map.size(), mips.size());
        assertEquals(0, mips.size());

        // negative tests

        assertEquals(null, mips.put(100500, "1"));
        assertNotEquals(map.size(), mips.size());

    }

    @Test
    void isEmptyTest() {
        assertEquals(map.isEmpty(), mips.isEmpty());
        assertEquals(true, mips.isEmpty());
        assertEquals(map.put(1, "1"), mips.put(1, "1"));
        assertEquals(map.isEmpty(), mips.isEmpty());
        assertEquals(false, mips.isEmpty());
        assertEquals(map.remove(1), mips.remove(1));
        assertEquals(map.isEmpty(), mips.isEmpty());
    }

    @Test
    void containsKeyTest() {
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
        assertEquals(map.containsValue("100500"), mips.containsValue("10050"));
        assertEquals(map.put(null, null), mips.put(null, null));
        assertEquals(map.containsValue(null), mips.containsValue(null));
        assertEquals(map.put(1, "null"), mips.put(1, "null"));
        assertEquals(map.containsValue("null"), mips.containsValue("null"));
    }

    @Test
    void putAllTest() {
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
    void clearTest() {
        assertEquals(map.put(1, "1"), mips.put(1, "1"));
        assertEquals(map.get(1), mips.get(1));
        assertEquals(map.size(), mips.size());
        map.clear();
        mips.clear();
        assertEquals(map.get(1), mips.get(1));
        assertEquals(map.size(), mips.size());
    }

    @Test
    void keySetTest() {
        mips.put(3, "-3");
        assertEquals(map.put(null, "zero"), mips.put(null, "zero"));
        assertEquals(map.put(0, null), mips.put(0, null));
        assertEquals(map.put(1, "-1"), mips.put(1, "-1"));
        assertEquals(map.put(2, "-2"), mips.put(2, "-2"));
        map.put(3, "-3");
        assertEquals(map.put(4, "-4"), mips.put(4, "-4"));
        assertEquals(map.size(), mips.size());
        Set<Integer> mapKeys = map.keySet();
        Set<Integer> mipsKeys = map.keySet();
        mapKeys.removeAll(mipsKeys);
        mipsKeys.removeAll(mapKeys);
        assertTrue(mapKeys.isEmpty());
        assertTrue(mipsKeys.isEmpty());
    }

    @Test
    void valuesTest() {
        mips.put(3, "-3");
        assertEquals(map.put(null, "zero"), mips.put(null, "zero"));
        assertEquals(map.put(0, null), mips.put(0, null));
        assertEquals(map.put(1, "-1"), mips.put(1, "-1"));
        assertEquals(map.put(2, "-2"), mips.put(2, "-2"));
        assertEquals(map.put(4, "-4"), mips.put(4, "-4"));
        map.put(3, "-3");
        assertEquals(map.size(), mips.size());
        Collection mapColl = map.values();
        Collection<? extends Object> mipsColl = mips.values();
        int beginSize = mipsColl.size();
        assertEquals(mapColl.size(), mipsColl.size());
        mapColl.retainAll(mipsColl);
        mipsColl.retainAll(mapColl);
        assertEquals(beginSize, mapColl.size());
        assertEquals(beginSize, mipsColl.size());
    }

    @Test
    void entrySetTest() {
        mips.put(3, "-3");
        assertEquals(map.put(null, "zero"), mips.put(null, "zero"));
        assertEquals(map.put(0, null), mips.put(0, null));
        assertEquals(map.put(1, "-1"), mips.put(1, "-1"));
        map.put(3, "-3");
        assertEquals(map.put(2, "-2"), mips.put(2, "-2"));
        assertEquals(map.put(4, "-4"), mips.put(4, "-4"));
        assertEquals(map.size(), mips.size());
        Set<? extends Map.Entry<Integer, String>> mapEntries = map.entrySet();
        Set<? extends Map.Entry<Integer, String>> mipsEntries = mips.entrySet();
        assertEquals(mapEntries.size(), mipsEntries.size());
        int mapLen = map.size();
        mapEntries.retainAll(mipsEntries);
        mipsEntries.retainAll(mapEntries);
        assertEquals(mapEntries.size(), mipsEntries.size());
        assertEquals(mapLen, mips.size());
    }

    @Test
    void equalsTest() {

        Map<Integer, String> mapOne = new HashMap<>();
        Map<Integer, String> mapTwo = new HashMap<>();

        mapOne.put(100, "hunter");
        mapTwo.put(100, "hunter");

        assertTrue(mapOne.equals(mapTwo));

        Mips<Integer, String> mipsOne = new Mips<>();
        Mips<Integer, String> mipsTwo = new Mips<>();

        mipsOne.put(100, "hunter");
        mipsOne.put(200, "200");
        mipsOne.put(null, null);
        mipsTwo.put(100, "hunter");
        mipsTwo.put(200, "200");

        assertFalse(mipsOne.equals(mipsTwo));

        mipsTwo.put(null, null);

        assertTrue(mipsOne.equals(mipsTwo));

    }


}