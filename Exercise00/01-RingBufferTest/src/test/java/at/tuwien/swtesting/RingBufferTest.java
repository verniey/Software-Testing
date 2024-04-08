package at.tuwien.swtesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * RingBufferTest
 *
 * Name: Veronika Zapodobnikova
 * Student-ID: 01328458
 * Exercise00-assignment01
 */
public class RingBufferTest {

	private RingBuffer<Integer> buffer;

	@BeforeEach
	void setUp() {
		buffer = new RingBuffer<>(3);
	}

	@Test
	void capacityReturnsInitializesCapacityTest() {
		assertEquals(3, buffer.capacity());
	}

	@Test
	void enqueueIncreaseSizeCorrectlyTest() {
		buffer.enqueue(1);
		assertEquals(1, buffer.size());
	}

	@Test
	void enqueueOverCapacityOverwriteCorrectlyTest() {
		buffer.enqueue(1);
		buffer.enqueue(2);
		buffer.enqueue(3);
		buffer.enqueue(4);
		assertEquals(Integer.valueOf(2), buffer.dequeue());
	}

	@Test
	void dequeueReduceSizeAndReturnsCorrectElementTest() {
		buffer.enqueue(1);
		buffer.enqueue(2);
		int firstDequeue = buffer.dequeue();
		assertEquals(1, firstDequeue);
		assertEquals(1, buffer.size());
	}

	@Test
	void peekReturnsFirstElementWithoutRemovingTest() {
		buffer.enqueue(1);
		buffer.enqueue(2);
		assertEquals(Integer.valueOf(1), buffer.peek());
		assertEquals(2, buffer.size());
	}

	@Test
	void isEmptyReturnsTrueForNewBuffer() {
		assertTrue(buffer.isEmpty());
	}

	@Test
	void isFullReturnsTrueWhenBufferIsFull() {
		buffer.enqueue(1);
		buffer.enqueue(2);
		buffer.enqueue(3);
		assertTrue(buffer.isFull());
	}

	@Test
	void dequeueFromEmptyBufferThrowsExceptionTest() {
		assertThrows(RuntimeException.class, () -> buffer.dequeue());
	}

	@Test
	void  peekFromEmptyBufferThrowsExceptionTest() {
		assertThrows(RuntimeException.class, () -> buffer.peek());
	}

	@Test
	void iteratorShouldIterateOverAllElementsTest() {
		buffer.enqueue(1);
		buffer.enqueue(2);
		buffer.enqueue(3);

		Iterator<Integer> iterator = buffer.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(Integer.valueOf(1), iterator.next());

		assertTrue(iterator.hasNext());
		assertEquals(Integer.valueOf(2), iterator.next());

		assertTrue(iterator.hasNext());
		assertEquals(Integer.valueOf(3), iterator.next());

		assertFalse(iterator.hasNext());
	}

	@Test
	void iteratorThrowsNoSuchElementExceptionWhenNoMoreElementsTest() {
		buffer.enqueue(1);
		Iterator<Integer> iterator = buffer.iterator();
		iterator.next();

		assertThrows(NoSuchElementException.class, iterator::next);
	}

	@Test
	void iteratorThrowsNoSuchElementExceptionWhenBufferIsEmptyTest() {
		Iterator<Integer> iterator = buffer.iterator();
		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, iterator::next);
	}

	@Test
	void iteratorDoesNotSupportRemoveOperationTest() {
		buffer.enqueue(1);
		Iterator<Integer> iterator = buffer.iterator();

		assertThrows(UnsupportedOperationException.class, iterator::remove);
	}

}