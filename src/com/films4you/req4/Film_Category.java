package com.films4you.req4;

/**
 * A class to represent which links films and their genre
 * 
 * @author Joseph Sim
 */

public class Film_Category {
	
	private int filmID;
	private int categoryID;
	
	/**
	 * Create a film's category with the film's ID and category's ID
	 
	 * @param filmID, The ID of the film, must be > 0.
	 * @param categoryID, The ID of the category, must be > 0.
	 * @throws IllegalArgumentException if the filmID or categoryID is invalid
	 */
	public Film_Category(int filmID, int categoryID) {
		if (filmID <= 0 || categoryID <= 0) {
			throw new IllegalArgumentException("Film ID and Category ID"
					+ " must be greater than zero");
		}
		
		this.filmID = filmID;
		this.categoryID = categoryID;
	}
	
	public int getFilmID() {
		return this.filmID;
	}
	
	public int getCategoryID() {
		return this.categoryID;
	}

}
