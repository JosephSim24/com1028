package com.films4you.req4;

/**
 * A class which represents the inventory of the Films4You stores
 * 
 * @author Joseph Sim
 */

public class Inventory {
	
	private int inventoryID;
	private int filmID;

	/**
	 * Create an inventory item with a given inventory ID and film ID
	 
	 * @param inventoryID, The ID of the item in inventory, must be > 0.
	 * @param filmID, The ID of the film in inventory, must be > 0.
	 * @throws IllegalArgumentException if the inventoryID or filmID is invalid
	 */
	public Inventory(int inventoryID, int filmID) {
		if (inventoryID < 0) {
			throw new IllegalArgumentException("Inventory ID "
					+ "must be greater than 0");
		}
		
		this.inventoryID = inventoryID;
		this.filmID = filmID;
	}
	
	public int getInventoryID() {
		return this.inventoryID;
	}
	
	public int getFilmID() {
		return this.filmID;
	}
	
}
