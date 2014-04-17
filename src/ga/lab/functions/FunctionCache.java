package ga.lab.functions;

import java.util.HashMap;

public class FunctionCache<M, V> {
    private HashMap<M, V> cache = new HashMap<>();

    public FunctionCache() {
    }

    public void add(M key, V value) {
        cache.put(key, value);
    }

    public V get(M key) {
        return cache.get(key);
    }
}
