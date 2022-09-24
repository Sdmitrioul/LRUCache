package skroba;

public interface Cache<K, V> {
	/**
	 * Return elements store under key value or null if it isn't presented. If element is presented value became more valuable.
	 *
	 * @param key - key by which value is stored.
	 * @return value under key or null.
	 */
	V get(K key);
	
	/**
	 * Add element in cache under key value.
	 *
	 * @param key   - key by which element can be got.
	 * @param value - value that is stored.
	 */
	void put(K key, V value);
	
	/**
	 * Remove value that is stored under key value or do nothing if element doesn't presented.
	 *
	 * @param key - key of element.
	 */
	void remove(K key);
	
	/**
	 * Show size of cache.
	 *
	 * @return - cache size.
	 */
	int size();
	
	/**
	 * Returns true if cache don't store anything, otherwise false.
	 *
	 * @return - if cache is empty.
	 */
	boolean isEmpty();
}
