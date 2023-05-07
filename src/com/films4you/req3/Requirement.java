package com.films4you.req3;

import com.films4you.main.Database;
import com.films4you.main.RequirementInterface;
import com.films4you.main.TaskNotAttemptedException;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Requirement: Find all actors with first name "Penelope"

 * @author Joseph Sim
 *
 */
public class Requirement implements RequirementInterface {
	
	/**
	 * Get all actors with their actor ID, first name and last name
	 
	 * @return List of actors
	 * @throws SQLException on database error
	 * @throws IllegalStateException on error, e.g. value null when not allowed
	 */
	private List<Actor> getActors() throws SQLException, IllegalStateException {
		Database db = new Database();
		List<Actor> allActors = new ArrayList<>();
		
		ResultSet rs = db.query("SELECT * FROM actor");
		if (rs == null) {
			throw new IllegalStateException("Query returned null");
		}
		
		while (rs.next()) {
			int actorID = rs.getInt(1);
			String firstName = rs.getString(2);
			String lastName = rs.getString(3);
			if (actorID >= 0 && firstName != null && lastName != null) {
				Actor actor = new Actor(actorID);
				actor.setFirstName(firstName);
				actor.setLastName(lastName);
				allActors.add(actor);
			}
		}
		
		db.close();
		return allActors;
		
	}
	
	
	private List<Actor> getActorsWithNamePenelope(List<Actor> actors) {
		List<Actor> penelopeList = new ArrayList<>();
		for (Actor actor : actors) {
			if (actor.getFirstName().equals("PENELOPE")) {
				penelopeList.add(actor);
			}
		}
		return penelopeList;
	}
  
	/**
	 * A method to return the number of actors with the first name "PENELOPE"
	 * and their full names
	 
	 * @return A string containing the number of actors with first name "PENELOPE"
	 * 		and each actor's full name. In the format "[TOTAL]:[FIRSTNAME]:[LASTNAME]:[ACTORID]:\n
	 * 		[FIRSTNAME]:[LASTNAME]:[ACTORID]:\n etc."
	 */
	@Override
  	public @Nullable String getValueAsString() {
	  	String output;
	  	List<Actor> penelopeList;
	  	try {
	  		penelopeList = new ArrayList<>(getActorsWithNamePenelope(getActors()));
	  		output = penelopeList.size() + ":";
	  		for (int i = 0; i < penelopeList.size(); i++) {
	  			output += penelopeList.get(i).toString() + ":\n";
	  		}
	  		return output;
	  	} 	
	  	catch (SQLException e) {
	  		e.printStackTrace();
	  		return null;
	  	}
  	}

	/**
	 * A method to get a parse the non human readable string from getValueAsString
	 * and return it in a human readable format
	 
	 * @return A string formatted for the end user containing the total
	 * number of actors with first name "PENELOPE" and each actor's full name and
	 * actor ID number. In the format "There are [TOTAL] actors that have the first name Penelope:\n
	 * [TOTAL]:\n[FIRSTNAME] [LASTNAME] (ID: [ACTORID])\n[FIRSTNAME] [LASTNAME] (ID: [ACTORID])\n etc."
	 */
  	@Override
  	public @NonNull String getHumanReadable() {
  		String valueAsString = getValueAsString();
  		if (valueAsString == null) {
  			return "No results found or an error has occured";
  		}
  		String output = "There are ";
  		output += valueAsString.split(":")[0];
  		output += " actors that have the first name Penelope:\n";
  		for (int i = 1; i < valueAsString.lines().count()*3 + 1; i = i + 3) {
  			output += valueAsString.split(":")[i] + " ";
  			output += valueAsString.split(":")[i + 1] + " (ID: ";
  			output += valueAsString.split(":")[i + 2] + ")";
  		}
  		return output;
  	}

}
