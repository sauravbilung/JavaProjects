package concurrency._3_Collections;

import java.util.concurrent.ConcurrentHashMap;

public class _1_ConcurrentHashMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.put("A", 1);
        map.putIfAbsent("B", 2);

        map.compute("A", (key, val) -> val == null ? 1 : val + 1);

        map.forEach((k, v) -> System.out.println(k + " = " + v));
    }
}