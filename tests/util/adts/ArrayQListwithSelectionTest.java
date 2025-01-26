package util.adts;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A class that tests the methods that the class
 * ArrayQListwithSelection uses.
 */
public class ArrayQListwithSelectionTest {
	
	@Test public void listSize() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.add(233);
		list.add(539);
		list.remove();
		assertTrue(list.size() == 2);
	}
	
	@Test public void addToList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		assertTrue(list.size() == 1);
	}
	
	@Test public void removeFromList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.remove();
		assertTrue(list.size() == 0);
	}
	
	@Test public void selectFromList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.add(233);
		list.add(234);
		list.select(1);
		assertTrue(list.getSelected() == 233);
	}
	
	@Test public void getFromList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.remove();
		list.add(232);
		list.add(233);
		list.get(1);
		assertTrue(list.get(1) == 233);
	}
	
	@Test public void emptyListIterator() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		assertFalse(list.iterator().hasNext());
	}
	
	@Test public void listIterator() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.add(233);
		list.iterator().next();
		assertTrue(list.iterator().next() == 232);
	}
	
	@Test public void someSelectedInList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.remove();
		list.add(232);
		list.add(233);
		list.add(234);
		list.remove();
		list.select(1);
		assertTrue(list.someSelected());
	}
	
	@Test public void noneSelectedInList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.remove();
		list.add(232);
		list.add(233);
		list.add(234);
		list.remove();
		assertFalse(list.someSelected());
	}
	
	@Test public void getIndexSelectedInList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.remove();
		list.add(232);
		list.add(233);
		list.add(234);
		list.add(235);
		list.select(2);
		assertTrue(list.getIndexSelected() == 2);
	}
	
	@Test public void noIndexSelectedInList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.remove();
		list.add(232);
		list.add(233);
		list.remove();
		list.remove();
		assertTrue(list.getIndexSelected() == -1);
	}
	
	@Test public void nextInList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.remove();
		list.add(232);
		list.add(233);
		list.add(234);
		list.add(235);
		list.select(2);
		list.next();
		assertTrue(list.getIndexSelected() == 3);
	}
	
	@Test public void noNextInList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.remove();
		list.add(232);
		list.add(233);
		list.add(234);
		list.add(235);
		list.select(3);
		list.next();
		assertTrue(list.getIndexSelected() == -1);
	}
	
	@Test public void previousInList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.remove();
		list.add(232);
		list.add(233);
		list.add(234);
		list.add(235);
		list.select(2);
		list.previous();
		assertTrue(list.getIndexSelected() == 1);
	}
	
	@Test public void noPreviousInList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.remove();
		list.add(232);
		list.add(233);
		list.add(234);
		list.add(235);
		list.select(0);
		list.previous();
		assertTrue(list.getIndexSelected() == -1);
	}
	
	@Test public void getSelectedInList() {
		ArrayQListWithSelection<Integer> list = new ArrayQListWithSelection<>();
		list.add(232);
		list.remove();
		list.add(232);
		list.add(233);
		list.add(234);
		list.add(235);
		list.select(2);
		assertTrue(list.getSelected() == 234);
	}
}

