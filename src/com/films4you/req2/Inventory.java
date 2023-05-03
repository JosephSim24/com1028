package com.films4you.req2;

/**
 * A class which represents the inventory of the Films4You stores
 */

public class Inventory {
	
	private int inventoryID;
	private int filmID;

	/**
	 * Create an inventory item with a given ID and title
	 
	 * @param inventoryID, The ID of the item in inventory, must be >= 0.
	 * @throws IllegalArgumentException if the inventoryID is invalid
	 */
	public Inventory(int inventoryID) {
		if (inventoryID < 0) {
			throw new IllegalArgumentException("Inventory ID "
					+ "must be greater than or equal to 0");
		}
		
		this.inventoryID = inventoryID;
	}
	
	public int getInventoryID() {
		return this.inventoryID;
	}
	
	public int getFilmID() {
		return this.filmID;
	}
	
	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}
	
}
