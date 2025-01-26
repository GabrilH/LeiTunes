package util.adts;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A abstract class that implements QListWithSelection<E>, a
 * list of elements E that allows new elements to be added only
 * at the end of the list, and has the ability to select one of
 * the elements of the list at a time. Has no specific type of
 * list implemented.
 * 
 * 
 * @param <E>
 */
public abstract class AbsQListWithSelection<E> implements QListWithSelection<E> {
	
	private List<E> list;
	private int selectedIndex;
	
	/**
	 * Constructor of the object, receives a list and sets the
	 * attribute selectedIndex at 
	 * 
	 * @param list the 
	 */
	protected AbsQListWithSelection(List<E> list) {
		this.list = list;
		this.selectedIndex = -1;
	}
	
	 /**
     * Returns the number of elements in the list.
     * 
     * @return the number of elements in the list
     */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Returns the element of the list in the given index
	 * 
	 * @param i the index
	 * @return the element of the list in the given index 
	 */
	@Override
	public E get(int i) {
		return list.get(i);
	}

	/**
	 * Returns an iterator for the list
	 * 
	 * @return an iterator for the list
	 */
	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	/**
	 * Selects the element in index i of the list
	 * 
	 * @param i
	 * @requires 0 ≤ i < size()
	 */
	@Override
	public void select(int i) {
		this.selectedIndex = i;
	}

	/**
	 * Adds an element at the end of the list and makes
	 * it selected
	 * 
	 * @ensures someSelected() == True
	 */
	@Override
	public void add(E e) {
		list.add(e);
		this.selectedIndex = size() - 1;
	}

	/**
	 * Indicates if there is a selected element
	 * 
	 * @return True if there is a selected element, False otherwise
	 */
	@Override
	public boolean someSelected() {
		return this.selectedIndex != -1;
	}

	/**
	 * Returns the index of the selected element
	 * 
	 * @requires someSelected() == True
	 * @return the index of the selected element
	 */
	@Override
	public int getIndexSelected() {
		return this.selectedIndex;
	}

	/**
	 * If getIndexSelected() < size() - 1 it selects the next element 
	 * of the list, otherwise the list stops having a selected element
	 * 
	 * @requires someSelected() == True
	 */
	@Override
	public void next() {
		
		if(getIndexSelected() < size() -1)
			selectedIndex++;
		else
			selectedIndex = -1;
	}

	/**
	 * If getIndexSelected() > 0 it selects the previous element of the
	 * list, otherwise the list stops having a selected element
	 * 
	 * @requires someSelected() == True
	 */
	@Override
	public void previous() {
		
		if(getIndexSelected() > 0)
			this.selectedIndex--;
		else
			this.selectedIndex = -1;
	}

	/**
	 * If someSelected() == True it removes the selected element of the
	 * list, otherwise nothing happens
	 */
	@Override
	public void remove() {
		
		if(someSelected()) {
			list.remove(selectedIndex);
			this.selectedIndex = -1;
		}
	}

	/**
	 * Returns the selected element
	 * 
	 * @requires someSelected() == True
	 * @return the selected element
	 */
	@Override
	public E getSelected() {
		return list.get(selectedIndex);
	}
	
}
