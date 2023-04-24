package com.films4you.req2;

/**
 * A class which represents the payment for renting out a film
 */

public class Payment {
	
	private int paymentID;
	private int rentalID;
	private int inventoryID;
	private int filmID;
	
	/**
	 * Create a payment with a given paymentID
	 
	 * @param paymentID, the ID of the payment, must be >= 0.
	 * @throws IllegalArgumentException if the paymentID is invalid.
	 */
	
	public Payment(int paymentID) {
		if (paymentID < 0) {
			throw new IllegalArgumentException("PaymentID cannot"
					+ " be less than zero.");
		}
		
		
		this.paymentID = paymentID;
	}
	
	public int getPaymentID() {
		return this.paymentID;
	}
	
	public int getRentalID() {
		return rentalID;
	}
	
	public int getInventoryID() {
		return inventoryID;
	}
	
	public int getFilmID() {
		return filmID;
	}
	
	/**
	 * Set a value for the rentalID
	 * 
	 * @param rentalID must be >= 0.
	 * @throws IllegalArgumentException if rentalID is invalid.
	 */
	
	public void setRentalID(int ID) {
		if (ID < 0) {
			throw new IllegalArgumentException("ID cannot be less than zero");
		}
		rentalID = ID;
	}
	
	/**
	 * Set a value for the inventoryID
	 * 
	 * @param inventoryID must be >= 0.
	 * @throws IllegalArgumentException if inventoryID is invalid.
	 */
	
	public void setInventoryID(int ID) {
		if (ID < 0) {
			throw new IllegalArgumentException("ID cannot be less than zero");
		}
		inventoryID = ID;
	}
	
	/**
	 * Set a value for the filmID
	 * 
	 * @param filmID, the ID of the film, must be >= 0.
	 * @throws IllegalArgumentException if filmID is invalid.
	 */
	
	public void setFilmID(int ID) {
		if (ID < 0) {
			throw new IllegalArgumentException("ID cannot be less than zero");
		}
		filmID = ID;
	}

}
 