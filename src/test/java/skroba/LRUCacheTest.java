package skroba;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheTest {
	private final static int DEFAULT_SIZE_OF_CACHE = 3;
	private LRUCache<Integer, Integer> cache;
	
	@BeforeEach
	public void init() {
		init(DEFAULT_SIZE_OF_CACHE);
	}
	
	private void init(int size) {
		cache = new LRUCache<>(size);
	}
	
	@Test
	void createLRUCache() {
		assertNotNull(cache);
	}
	
	@Test
	void createLRUWithGivenSize() {
		final int size = 6;
		init(size);
		assertEquals(size, cache.size());
	}
	
	@Test
	void testPutInCache() {
		cache.put(1, 1);
		cache.put(2, 2);
		cache.put(3, 3);
		
		assertEquals(1, (int) cache.get(1));
		assertEquals(2, (int) cache.get(2));
		assertEquals(3, (int) cache.get(3));
	}
	
	@Test
	void testDeleteLastPriority() {
		cache.put(4, 4);
		cache.put(1, 1);
		cache.put(2, 2);
		cache.put(3, 3);
		
		assertNull(cache.get(4));
		assertEquals(1, (int) cache.get(1));
		assertEquals(2, (int) cache.get(2));
		assertEquals(3, (int) cache.get(3));
	}
	
	@Test
	void testPutWithSameKey() {
		cache.put(1, 1);
		cache.put(2, 2);
		cache.put(3, 3);
		cache.put(1, 2);
		
		assertEquals(2, (int) cache.get(1));
		assertEquals(2, (int) cache.get(2));
		assertEquals(3, (int) cache.get(3));
	}
	
	@Test
	void testSize() {
		init(4);
		assertEquals(4, cache.size());
		
		init(7);
		assertEquals(7, cache.size());
	}
	
	@Test
	void testGet() {
		cache.put(4, 4);
		cache.put(1, 1);
		cache.put(2, 2);
		cache.put(3, 3);
		
		cache.get(1);
		cache.get(2);
		cache.put(4, 4);
		
		assertNull(cache.get(3));
	}
	
	@Test
	void testRemove() {
		cache.put(4, 4);
		cache.put(1, 1);
		cache.put(2, 2);
		cache.put(3, 3);
		
		cache.remove(3);
		
		assertEquals(1, (int) cache.get(1));
		assertEquals(2, (int) cache.get(2));
		assertNull(cache.get(3));
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(cache.isEmpty());
		
		cache.put(3, 3);
		cache.remove(3);
		
		assertTrue(cache.isEmpty());
		
	}
}
