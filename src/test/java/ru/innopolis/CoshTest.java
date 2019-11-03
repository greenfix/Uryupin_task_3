package ru.innopolis;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

class CoshTest {

    private Cosh<Integer, String> cosh = new Cosh<>();
    private Map<Integer, String> map = new HashMap<>();

    @Test
    void put() {
        assertEquals(map.put(1, "1"),  cosh.put(1,"1"));
        assertEquals(map.get(1),  cosh.get(1));
        assertEquals(map.size(),  cosh.size());

        assertEquals(map.put(1, null),  cosh.put(1,null));
        assertEquals(map.get(1),  cosh.get(1));
        assertEquals(map.size(),  cosh.size());

        assertEquals(map.get(null),  cosh.get(null));
        assertEquals(map.put(null, "NNN"),  cosh.put(null,"NNN"));
        assertEquals(map.get(null),  cosh.get(null));
        assertEquals(map.size(),  cosh.size());
        assertEquals(map.put(null, null),  cosh.put(null,null));
        assertEquals(map.get(null),  cosh.get(null));
        assertEquals(map.size(),  cosh.size());

        Double rand;;
        for (int i = 2; i < 500; i++) {
            rand = Math.random();
            assertEquals(map.put(i, rand.toString()),  cosh.put(i,rand.toString()));
        }
        assertEquals(map.size(),  cosh.size());
        for (int i = 2; i < 500; i++) {
            assertEquals(map.get(i),  cosh.get(i));
        }
    }
}