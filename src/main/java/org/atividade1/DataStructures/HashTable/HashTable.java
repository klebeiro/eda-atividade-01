package org.atividade1.DataStructures.HashTable;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

public class HashTable<K, V> {
    private static final int INITIAL_SIZE = 10;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;
    private LinkedList<Entry<K, V>>[] table;
    private int size;

    public HashTable() {
        table = new LinkedList[INITIAL_SIZE];
        size = 0;
    }

    public void put(K key, V value) {
        if (size >= table.length * LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int index = getIndex(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);

        if (table[index] != null) {
            LinkedList<Entry<K, V>> bucket = table[index];

            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);

        if (table[index] != null) {
            LinkedList<Entry<K, V>> bucket = table[index];

            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    bucket.remove(entry);
                    size--;
                    return;
                }
            }
        }
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    private void resize() {
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[2 * oldTable.length];
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    @Data
    @AllArgsConstructor
    private static class Entry<K, V> {
        private K key;
        private V value;
    }
}