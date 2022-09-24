package skroba;

import java.util.HashMap;
import java.util.LinkedList;

public class LRUCache<K, V> implements Cache<K, V> {
	private final static int DEFAULT_CACHE_SIZE = 3;
	
	private final int cacheSize;
	private final LinkedList<K> prioritizedKeys;
	private final HashMap<K, V> store;
	
	LRUCache() {
		this(DEFAULT_CACHE_SIZE);
	}
	
	/**
	 * Create LRUCache with set size.
	 *
	 * @param cacheSize - positive value of cache size.
	 */
	public LRUCache(int cacheSize) {
		if (cacheSize <= 0) {
			throw new IllegalArgumentException("Cache size must have positive value");
		}
		
		this.cacheSize = cacheSize;
		this.prioritizedKeys = new LinkedList<>();
		this.store = new HashMap<>(cacheSize);
	}
	
	@Override
	public V get(K key) {
		V value = store.get(key);
		
		updateKey(key);
		
		return value;
	}
	
	@Override
	public void put(K key, V value) {
		if (store.containsKey(key)) {
			updateKey(key);
			store.put(key, value);
			
			return;
		}
		
		if (isFull()) {
			K removedKey = prioritizedKeys.removeLast();
			store.remove(removedKey);
		}
		
		prioritizedKeys.addFirst(key);
		store.put(key, value);
		
		assert store.containsKey(key) && store.containsValue(value);
	}
	
	@Override
	public void remove(K key) {
		if (prioritizedKeys.remove(key)) {
			store.remove(key);
		}
		
		assert !store.containsKey(key);
	}
	
	@Override
	public int size() {
		assert cacheSize > 0;
		return this.cacheSize;
	}
	
	@Override
	public boolean isEmpty() {
		return prioritizedKeys.isEmpty();
	}
	
	private boolean isFull() {
		return store.size() == cacheSize;
	}
	
	private void updateKey(K key) {
		if (prioritizedKeys.remove(key)) {
			prioritizedKeys.addFirst(key);
		}
	}
}
