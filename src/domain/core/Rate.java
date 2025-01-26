package domain.core;

/**
 * 
 * @author Gabriel Henriques fc58182
 * @author Guilherme Sousa fc58170
 *
 * A class whose immutable objects represent possible values 
 * of music classifications
 */
public final class Rate {
	
	public static final int MAX_RATING = 10;
	public static final int MIN_RATING = 0;
	private final int RATING;
	
	/**
	 * Constructor of the object with the given classification
	 * @param rating the given classification
	 */
	public Rate(int rating) {
		this.RATING = rating;
	}
	
	/**
	 * Returns if possible a new object with a classification 1 lower 
	 * than this.RATING, otherwise returns this object
	 * 
	 * @return a object with same or lower 
	 */
	public Rate decRating() {
		return this.RATING == MIN_RATING ? this : new Rate(RATING - 1);
	}
	
	/**
	 * Returns if possible a new object with a classification 1 higher 
	 * than this.RATING, otherwise returns this object
	 * 
	 * @return a object with same or higher classification 
	 */
	public Rate incRating() {
		return this.RATING == MAX_RATING ? this : new Rate(RATING + 1);
	}
	
	/**
	 * Compares the current rating with another given rating
	 * 
	 * @param other the given rating
	 * @return -1 if other is a higher rating, 0 if they are equal and 1 if other is a lower rating
	 */
	public int compareTo(Rate other) {
		return Integer.compare(this.RATING, other.RATING);
	}
	
	/**
	 * Checks if the current rating is greater than the given rating
	 * 
	 * @param other the given rating
	 * @return true if the current rating is higher, false otherwise
	 */
	public boolean isGreaterThan(Rate other) {
	    return this.compareTo(other) > 0;
	}
	
	/**
	 * Checks if the current rating is lower than the given rating
	 * 
	 * @param other the given rating
	 * @return true if the current rating is lower, false otherwise
	 */
	public boolean isLessThan(Rate other) {
	    return this.compareTo(other) < 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + RATING;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Rate))
			return false;
		Rate other = (Rate) obj;
		return RATING == other.RATING;
	}

	@Override
	public String toString() {
		return String.valueOf(this.RATING);
	}

}
