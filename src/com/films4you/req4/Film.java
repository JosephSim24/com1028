package com.films4you.req4;

/**
 * A class which represents a film
 * 
 * @author Joseph Sim
 */

public class Film {
	
	private int filmID;
	private String title;
	private int rentCount = 0;
	
	/**
	 * Create a film with a given ID and name
	 
	 * @param filmID, The ID of the film, must be > 0.
	 * @param title, The title of the film, must not be null.
	 * @throws IllegalArgumentException if the filmID or title is invalid
	 */
	public Film(int filmID, String title) {
		if (filmID < 0 || title == null) {
			throw new IllegalArgumentException("The filmID cannot "
					+ "be less than or equal to zero");
		}
		
		this.filmID = filmID;
		this.title = title;
	}
	
	public int getFilmID() {
		return this.filmID;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getRentals() {
		return this.rentCount;
	}
	
	public void addRental() {
		rentCount++;
	}
	
	@Override
	public String toString() {
		return title + ":" + filmID;
	}

}
