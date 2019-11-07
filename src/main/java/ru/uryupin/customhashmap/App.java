package ru.uryupin.customhashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {


        Map<String, String> map = new HashMap<>();

        map.put("1", "Понедельник");
        map.put("2", "Вторник");
        map.put("3", "Среда");

        for(Map.Entry<String,String> pair : map.entrySet()){
            System.out.println(pair.getValue());
        }


        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<String, String> pa = entries.next();
            System.out.println(pa.getKey());
        }


    }
}
