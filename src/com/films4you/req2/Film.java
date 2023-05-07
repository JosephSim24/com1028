package com.films4you.req2;

/**
 * A class which represents a film
 */

public class Film {
	
	private int filmID;
	private String title;
	
	/**
	 * Create a film with a given ID
	 
	 * @param filmID, The ID of the film, must be >= 0.
	 * @throws IllegalArgumentException if the filmID is invalid
	 */
	public Film(int filmID) {
		if (filmID < 0) {
			throw new IllegalArgumentException("The filmID cannot"
					+ "be less than zero");
		}
		
		this.filmID = filmID;
	}
	
	public int getFilmID() {
		return this.filmID;
	}
	
	public String getTitle() {
		return this.title;
	}
		
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return title + ":" + filmID;
	}

}
