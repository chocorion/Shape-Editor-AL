package application.utils;

/**
 * Represent a pair of object
 * @param <K> First element of the pair.
 * @param <V> Second element of the pair.
 */
public class Pair<K, V> {
    private K key;
    private V value;

    /**
     * Parameterized constructor.
     * @param key The first element of the pair.
     * @param value The second element of the pair.
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }


    /**
     * Return the first element of the pair.
     * @return First element.
     */
    public K getKey() {
        return this.key;
    }


    /**
     * Return the second element of the pair.
     * @return Second element of the pair.
     */
    public V getValue() {
        return this.value;
    }
}
