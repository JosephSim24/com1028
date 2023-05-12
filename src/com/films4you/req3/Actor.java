package com.films4you.req3;

/**
 * A class which represents an actor
 * 
 * @author Joseph Sim
 */

public class Actor {
	
	private int actorID;
	private String firstName;
	private String lastName;
	
	/**
	 * Create an actor with a given ID
	 
	 * @param actorID, The ID of the actor, must be >= 0.
	 * @throws IllegalArgumentException if the actorD is invalid
	 */
	public Actor(int actorID) {
		if (actorID < 0) {
			throw new IllegalArgumentException("Actor ID must be greater than or equal to zero");
		}
		
		this.actorID = actorID;
	}
	
	public int getActorID() {
		return this.actorID;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return this.firstName + ":" + this.lastName + ":" + this.actorID;
	}

}
