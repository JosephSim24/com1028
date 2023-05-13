package com.films4you.req2;

import com.films4you.main.Database;
import com.films4you.main.RequirementInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Requirement: Find the top 10 most popular films based on
 * the number of rentals.

 * @author Joseph Sim
 *
 */
public class Requirement implements RequirementInterface {	
	
	private Map<Film, Integer> rentCountFilms;
	
	/**
	 * A method which returns the top 10 films based on the number of rentals
	 * 
	 * @return An array list consisting of the top 10 films based on number
	 * of rentals of each film.
	 * @throws SQLException on database error
	 * @throws IllegalStateException on error, e.g. when value is null 
	 * when it shouldn't be
	 */
	
	private Film[] getTop10Films() throws SQLException, IllegalStateException {
	    Database db = new Database();
	    List<Rental> allRentals = new ArrayList<>();
	    List<Inventory> allInventory = new ArrayList<>();
	    List<Film> allFilms = new ArrayList<>();
	    rentCountFilms = new HashMap<Film, Integer>();
	    Film[] orderedFilms;
	    
	 /*
	  * Retrieve all the inventoryIDs and matches them with the
	  * with the payments using the rentalIDs as the constant
	  */
	    ResultSet rs = db.query("SELECT * FROM rental");
	    if (rs == null) {
	    	throw new IllegalStateException("Query returned null");
	    }
	    
	    while (rs.next()) {
	    	int rentalID = rs.getInt(1);
	    	int inventoryID = rs.getInt(3);
	    	Rental rental = new Rental(rentalID);
	    	rental.setInventoryID(inventoryID);
	    	allRentals.add(rental);
	    }
	    
	    
	    /*
		  * Retrieve all the filmIDs and matches them with the
		  * with the payments using the inventoryIDs as the constant
		  */
		 rs = db.query("SELECT * FROM inventory");
		 if (rs == null) {
		 	throw new IllegalStateException("Query returned null");
		 }
		    
		 while (rs.next()) {
			 int inventoryID = rs.getInt(1);
			 int filmID = rs.getInt(2);
			 Inventory item = new Inventory(inventoryID);
			 item.setFilmID(filmID);
			 allInventory.add(item);
		 }
		    
	    
	    // Retrieve all the films and puts them in a list
	    rs = db.query("SELECT * FROM film");
	    if (rs == null) {
	    	throw new IllegalStateException("Query returned null");
	    }
	    	
	    while (rs.next()) {
	    	int filmID = rs.getInt(1);
	    	String title = rs.getString(2);
	    	Film film = new Film(filmID);
	    	film.setTitle(title);
	    	allFilms.add(film);
	    }
	    db.close();
	    
	    /*
	     * Cycles through all rentals and all items in the inventory and when there is a similarity,
	     * finds which film the inventory item is referring to and adds 1 to the rental count for that film
	     */
	    for (int rental = 0; rental < allRentals.size() - 1; rental++) {
	    	for (int item = 0; item < allInventory.size() - 1; item++) {
	    		if (allRentals.get(rental).getInventoryID() == allInventory.get(item).getInventoryID()) {
	    			for (int film = 0; film < allFilms.size() - 1; film++) {
	    				if (allFilms.get(film).getFilmID() == allInventory.get(item).getFilmID()) {
	    					if (!rentCountFilms.containsKey(allFilms.get(film))) {
	    						rentCountFilms.put(allFilms.get(film), 1);
	    					}	else {
	    						int temp = rentCountFilms.get(allFilms.get(film)) + 1;
	    	    				rentCountFilms.remove(allFilms.get(film));
	    	    				rentCountFilms.put(allFilms.get(film), temp);
	    					}
	    				}
	    			}
	    		}
	    	}
	   	}
	    
	    /*
	     * Sorting the hashmap in descending order and storing 
	     * it in a hashmap called "sortedMap"
	     */
	    LinkedHashMap<Film, Integer> sortedMap = new LinkedHashMap<>();
		List<Integer> values = new ArrayList<>();
		for (Map.Entry<Film, Integer> entry : rentCountFilms.entrySet()) {
			values.add(entry.getValue());
		}
		Collections.sort(values, Collections.reverseOrder());
		for (int num : values) {
			for (Entry<Film, Integer> entry : rentCountFilms.entrySet()) {
				if (entry.getValue().equals(num)) {
					sortedMap.put(entry.getKey(), num);
				}
			}
		}
	    
		/*
		 * Creates an array of all films that have been rented in descending
		 * order based on number of rentals of that film
		 */
	    orderedFilms = new Film[sortedMap.size()];
	    int count = 0;
	    for (Film film : sortedMap.keySet()) {
	    	orderedFilms[count] = film;
	    	count++;
	    }
	    
	    /*
	     * Sorts the ordered films alphabetically if the films have been rented the same number
	     * of times
	     */
	    for (int i = 0; i < orderedFilms.length; i++) {
	    	for (int j = i + 1; j < orderedFilms.length; j++) {
	    		if (rentCountFilms.get(orderedFilms[i]) == rentCountFilms.get(orderedFilms[j])) {
	    			if (orderedFilms[i].getTitle().compareTo(orderedFilms[j].getTitle()) > 0) {
	    				Film tempFilm = orderedFilms[i];
	    				orderedFilms[i] = orderedFilms[j];
	    				orderedFilms[j] = tempFilm;
	    			}
	    		}
	    	}
	    }
	    
	    /*
	     * Creates an array of only the first 10 films from the array "orderedFilms"
	     */
	    Film[] top10Films = new Film[10];
	    for (int i = 0; i < 10; i++) {
	    	top10Films[i] = orderedFilms[i];
	    }
	    
	    for (int i = 0; i < 10; i++) {
	    	
	    }
	    
		return top10Films;
	}
	
	
	/**
	 * A method which returns the top 10 rented films in a non-human-
	 * readable format.
	 
	 * @return A String containing the 10 top rented films each
	 * separated by colons in the format "[COUNT]:[FILMTITLE]:[FILMID]:[RENTCOUNT]:\n
	 * [COUNT]:[FILMTITLE]:[FILMID]:[RENTCOUNT]:\n etc.".
	 */
  @Override
  public @Nullable String getValueAsString() {
	  String output = "";
	  Film[] top10Films;
	try {
		top10Films = getTop10Films();
		for (int i = 0; i < top10Films.length; i++) {
			output += i+1 + ":" + top10Films[i].toString() + ":" + rentCountFilms.get(top10Films[i]) + ":\n";
		}
		return output;
	}
	 catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
  }

  /**
   * A method to parse the String from getValueAsString and return
   * it in a human-readable-format.
    
   * @return A String formatted for the end user containing the
   * top 10 rented films and their IDs. In the format 
   * "The top 10 films based on their number of rentals is:\n
   * 1. [FILM] (ID: [FILMID]) has been rented [COUNT] times\n
   * 2. [FILM] (ID: [FILMID]) has been rented [COUNT] times\n
   * 3. etc.".
   */
  @Override
  public @NonNull String getHumanReadable() {
	  String valueAsString = getValueAsString();
	  if (valueAsString == null) {
		  return "No results found or an error has occurred";
	  }
	  String output = "The top 10 films based on their number of rentals is:\n";
	  for (int p = 0; p < valueAsString.lines().count()*4; p = p+4) {
		  output += valueAsString.split(":")[p] + ". ";
		  output += valueAsString.split(":")[p + 1] + " (ID: ";
		  output += valueAsString.split(":")[p + 2] + ") has been rented ";
		  output += valueAsString.split(":")[p + 3] + " times";
	  }
	  return output;
  }
}
