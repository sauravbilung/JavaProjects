package concurrency._3_Collections;

import java.util.concurrent.ConcurrentSkipListMap;

public class _4_ConcurrentSkipListMapExample {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(3, "Three");
        map.put(1, "One");
        map.put(2, "Two");

        map.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}

/*
 * Combination of sorted linkedList and sorted array :
 * sorted linked list : search O(n) , update : O(1) after search
 * sorted array : search : O(log n) , update : O(n) n shifts after deletion
 *
 * We can use balanced binary search tree but it is complicated. Balancing is not simple.
 *
 * What is a skip list :
 *
 * A Skip List is a layered, probabilistic data structure that allows fast search, insertion, and deletion — similar to balanced binary search trees (like AVL or Red-Black Trees), but conceptually simpler.
 * It was invented as an alternative to complex tree rebalancing by using multiple levels of linked lists.
 *
 * 🔧 Basic Structure:
 * Imagine we have a sorted linked list:
 * Level 0:  1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9
 * If we want to find 8, you must traverse almost every element — O(n) time.
 * So, in a Skip List, we add "express lanes" (higher levels) that skip over some nodes.
 * Level 2:     1 ------------→ 5 ------------→ 9
 * Level 1:     1 → 3 → 5 → 7 → 9
 * Level 0:     1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9
 *
 * Level 0 is the full linked list.
 * Level 1 skips some elements.
 * Level 2 skips even more.
 * Each node randomly decides how many levels it appears on (e.g., a coin flip).
 *
 * 🔎 Search Example:
 *
 * To find 8:
 *   Start at Level 2: 1 → 5 → 9 (overshot)
 *   Drop to Level 1: 5 → 7 → 9 (still overshot)
 *   Drop to Level 0: 7 → 8 ✅
 *   This allows logarithmic search time, like a binary search tree.
 *
 * 📊 Time Complexities (Average Case): search, insert and delete is O(log n). Worst-case is O(n), but this is rare with good randomization.
 *
 *
 * ⚙️ Why is it used in Java?
      Java uses Skip Lists in ConcurrentSkipListMap and ConcurrentSkipListSet because:
      They allow lock-free concurrent reads.
      They don’t need rebalancing like trees.
      Probabilistic structure makes them easier to implement and maintain.
 *
 * 🎯 Summary:
     Skip Lists are like multi-level linked lists for fast search.
     Randomized structure replaces strict balancing (like trees).
     Useful in concurrent scenarios because you can update and search with fewer locks.

 * Important methods : put(K key, V value)
    | Method                       | Description                         |
| ---------------------------- | ----------------------------------- |
| `put(K key, V value)`        | Inserts or updates a key-value pair |
| `get(K key)`                 | Retrieves value for a key           |
| `remove(K key)`              | Deletes the key and its mapping     |
| `containsKey(K key)`         | Checks if key exists                |
| `firstKey()` / `lastKey()`   | Smallest / largest key              |
| `higherKey(K key)`           | Smallest key > given key            |
| `lowerKey(K key)`            | Largest key < given key             |
| `ceilingKey(K key)`          | Smallest key ≥ given key            |
| `floorKey(K key)`            | Largest key ≤ given key             |
| `headMap(K toKey)`           | View of keys less than `toKey`      |
| `tailMap(K fromKey)`         | View of keys ≥ `fromKey`            |
| `subMap(K fromKey, K toKey)` | View of keys in range               |
| `keySet()`                   | Returns a sorted set of keys        |
| `entrySet()`                 | Returns a sorted set of map entries |

 */