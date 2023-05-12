package com.films4you.req4;

/**
 * A class which represents the rental of a film
 * 
 * @author Joseph Sim
 */

public class Rental {
	
	private int rentalID;
	private int inventoryID;
	
	/**
	 * Create a rental with a given rental ID and inventory ID
	 
	 * @param rentalID, the ID of the rental, must be > 0.
	 * @param inventoryID, the ID of the inventory item rented, must be > 0.
	 * @throws IllegalArgumentException if the rentalID or inventoryID is invalid.
	 */
	
	public Rental(int rentalID, int inventoryID) {
		if (rentalID < 0 || inventoryID < 0) {
			throw new IllegalArgumentException("Rental ID and inventory ID "
					+ "must be greater than 0");
		}
		
		this.rentalID = rentalID;
		this.inventoryID = inventoryID;
	}
	
	
	public int getRentalID() {
		return this.rentalID;
	}
	
	public int getInventoryID() {
		return this.inventoryID;
	}

}
