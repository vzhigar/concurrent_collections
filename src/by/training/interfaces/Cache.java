package by.training.interfaces;

import java.util.Map;

public interface Cache<K, V> {
    void put(K key, V value);

    V get(K key);

    int size();

    default String printItems(Map<K, V> map) {
        final String whitespace = " ";
        final String newLine = "\n";
        final String items = "Items:\n";
        final String key = "key: ";
        final String value = "value: ";
        final String cacheSize = "Cache size: ";
        StringBuilder builder = new StringBuilder();
        builder.append(cacheSize)
                .append(size())
                .append(newLine)
                .append(items);
        map.forEach((k, v) -> builder.append(key)
                                    .append(k)
                                    .append(whitespace)
                                    .append(value)
                                    .append(v)
                                    .append(newLine)
        );
        return builder.toString();
    }

}
