package com.gunuputi.funcode.maps;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Leet Code: 146, LRU Cache
 */
public class LRUCache {

    Map<Integer, CacheElement> cacheMap;
    int maxCapacity;
    int currentSize;
    LinkedList<CacheElement> elementAccessTracker;

    public LRUCache(int capacity) {
        cacheMap = new HashMap<>();
        maxCapacity = capacity;
        elementAccessTracker = new LinkedList<>();
    }

    public int get(int key) {
        CacheElement cacheElement = cacheMap.get(key);
        if (cacheElement == null) {
            return -1;
        }
        elementAccessTracker.remove(cacheElement);
        elementAccessTracker.addLast(cacheElement);
        return cacheElement.value;
    }

    public void put(int key, int value) {
        if (cacheMap.containsKey(key)) {
            // don't do anything
            return;
        }
        if (currentSize == maxCapacity) {
            // delete Least Recently used element which will be at the head of the queue
            CacheElement lruElement = elementAccessTracker.removeFirst();
            cacheMap.remove(lruElement.key);
            currentSize--;
        }
        CacheElement cacheElement = CacheElement.of(key, value);
        elementAccessTracker.addLast(cacheElement);
        cacheMap.put(key, cacheElement);
        currentSize++;
    }

    public static class CacheElement {
        public int key;
        public int value;

        public static CacheElement of(int key, int val) {
            CacheElement newElem = new CacheElement();
            newElem.key = key;
            newElem.value = val;
            return newElem;
        }

        @Override
        public String toString() {
            return "[Key, Value] : [" + key + ", " + value + "]";
        }
    }

    public static void main(String[] args) {
        sampleTest();
    }

    private static void sampleTest() {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        boolean isTesCasePassed = lruCache.get(1) == -1;
        isTesCasePassed = lruCache.get(2) == 2;
        isTesCasePassed &= lruCache.get(3) == 3;
        isTesCasePassed &= lruCache.get(4) == 4;
        lruCache.put(5, 5);
        isTesCasePassed &= lruCache.get(2) == -1;
        if (isTesCasePassed) {
            System.out.println("Test Case Succeeded!");
        } else {
            System.out.println("Test Case Failed!");
        }
    }
}
