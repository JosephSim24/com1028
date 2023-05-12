package com.films4you.req4;

/**
 * A class to represent film categories
 * 
 * @author Joseph Sim
 */

public class Category {
	
	private int categoryID;
	private String name;
	
	/**
	 * Create a category with a given ID and name
	 
	 * @param categoryID, The ID of the category, must be > 0.
	 * @param name, The name of the film, must not be null.
	 * @throws IllegalArgumentException if the filmID is invalid
	 */
	public Category(int categoryID, String name) {
		if (categoryID <= 0 || name == null) {
			throw new IllegalArgumentException("Category ID cannot be "
					+ "less than or equal to zero and name must not be null");
		}
		
		this.categoryID = categoryID;
		this.name = name;
	}
	
	public int getCategoryID() {
		return this.categoryID;
	}
	
	public String getName() {
		return this.name;
	}

}
