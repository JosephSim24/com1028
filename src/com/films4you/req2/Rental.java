package com.films4you.req2;

/**
 * A class which represents the rental of a film
 */

public class Rental {
	
	private int rentalID;
	private int inventoryID;
	
	/**
	 * Create a rental with a given rentalID
	 
	 * @param rentalID, the ID of the rental, must be >= 0.
	 * @throws IllegalArgumentException if the rentalID is invalid.
	 */
	
	public Rental(int rentalID) {
		if (rentalID < 0) {
			throw new IllegalArgumentException("Rental ID "
					+ "must be greater than or equal to 0");
		}
		
		this.rentalID = rentalID;
	}
	
	
	public int getRentalID() {
		return this.rentalID;
	}
	
	public int getInventoryID() {
		return this.inventoryID;
	}
	
	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}

}
