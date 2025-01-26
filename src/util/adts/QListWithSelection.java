package util.adts;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A list of elements E that allows new elements to be added only
 * at the end of the list, and has the ability to select one of
 * the elements of the list at a time
 * 
 * @param <E>
 */
public interface QListWithSelection<E> extends QList<E> {

	/**
	 * Selects the element in index i of the list
	 * 
	 * @param i
	 * @requires 0 ≤ i < size()
	 */
	void select(int i);
	
	/**
	 * Adds an element at the end of the list and makes
	 * it selected
	 * 
	 * @ensures someSelected() == True
	 */
	@Override
	void add(E e);
	
	/**
	 * Indicates if there is a selected element
	 * 
	 * @return True if there is a selected element, False otherwise
	 */
	boolean someSelected();
	
	/**
	 * Returns the index of the selected element
	 * 
	 * @requires someSelected() == True
	 * @return the index of the selected element
	 */
	int getIndexSelected();
	
	/**
	 * If getIndexSelected() < size() - 1 it selects the next element 
	 * of the list, otherwise the list stops having a selected element
	 * 
	 * @requires someSelected() == True
	 */
	void next();
	
	/**
	 * If getIndexSelected() > 0 it selects the previous element of the
	 * list, otherwise the list stops having a selected element
	 * 
	 * @requires someSelected() == True
	 */
	void previous();
	
	/**
	 * If someSelected() == True it removes the selected element of the
	 * list, otherwise nothing happens
	 */
	void remove();
	
	/**
	 * Returns the selected element
	 * 
	 * @requires someSelected() == True
	 * @return the selected element
	 */
	E getSelected();
}
