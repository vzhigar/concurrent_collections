package by.training.impls;

import by.training.interfaces.Cache;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class LRUCache<K, V> implements Cache<K, V> {
    private final int capacity;
    private final Map<K, V> map;
    private final ReentrantReadWriteLock rwLock;
    private final Lock readLock;
    private final Lock writeLock;


    public LRUCache(final int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<K, V>(capacity, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
        rwLock = new ReentrantReadWriteLock();
        readLock = rwLock.readLock();
        writeLock = rwLock.writeLock();
    }

    @Override
    public void put(K key, V value) {
        writeLock.lock();
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }

    }

    @Override
    public V get(K key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }

    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return map.size();
        } finally {
            readLock.unlock();
        }

    }

    @Override
    public String toString() {
        readLock.lock();
        try {
            return printItems(map);
        } finally {
            readLock.unlock();
        }

    }
}
