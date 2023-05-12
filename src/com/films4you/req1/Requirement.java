package com.films4you.req1;

import com.films4you.main.Database;
import com.films4you.main.RequirementInterface;
import com.films4you.main.TaskNotAttemptedException;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Requirement: Find the total number of actors.
 * User story: As an Inventory Manager of Film4You I
 * need to find the total number of actors so that
 * our movies can appeal to a larger audience.

 * @author Joseph Sim
 *
 */
public class Requirement implements RequirementInterface {
	
	/**
	 * A method that returns the total number of actors
	 * 
	 * @return An integer to represent the number of actors
	 * @throws SQLException on database error
	 * @throws IllegalStateException on error, e.g. value null when not allowed
	 */
	public int getActors() throws SQLException, IllegalStateException {
		Database db = new Database();
		int actorCount = 0;
		
		ResultSet rs = db.query("SELECT * FROM actor");
		if (rs == null) {
			throw new IllegalStateException("Query returned null");
		}
		
		while (rs.next()) {
			actorCount++;
		}
		
		db.close();
		return actorCount;
	}
	
	/**
	 * A method that returns the total number of actors
	 
	 * @return A string containing the total number of actors.
	 */
  @Override
  public @Nullable String getValueAsString() {
    try {
    	String totalActors = String.valueOf(getActors());
    	return totalActors;
    }	
    catch (SQLException e) {
    	e.printStackTrace();
    	return null;
    }
  }

  /**
   * A method to parse the value from getValueAsString and return
   * it in a human-readable-format.
   
   * @return A string formatted for the end user containing the
   * total number of actors in the format "The total number of 
   * actors is [INTEGER]"
   */
  @Override
  public @NonNull String getHumanReadable() {
    String value = getValueAsString();
    if (value == null) {
    	return "No results found or an error has occured.";
    }
    
    return "The total number of actors is " + value;
  }

}