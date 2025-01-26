package util.adts;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A concrete class of AbsQListWithSelection<E>, a
 * list of elements E that allows new elements to be added only
 * at the end of the list, and has the ability to select one of
 * the elements of the list at a time. Implements using ArrayList
 * 
 * 
 * @param <E>
 */
public final class ArrayQListWithSelection<E> extends AbsQListWithSelection<E> {
	
	/**
	 * Constructor of the object
	 */
	public ArrayQListWithSelection() {
		super(new ArrayList<>());
	}
	
	/**
	 * Constructor of the object with a predefined list
	 */
	public ArrayQListWithSelection(List<E> list) {
		super(new ArrayList<>(list));
	}
	
	/**
	 * Constructor of the object with a predefined list and predefined selected index
	 */
	public ArrayQListWithSelection(List<E> list, int selectedIndex) {
		super(new ArrayList<>(list));
		super.select(selectedIndex);
	}

}