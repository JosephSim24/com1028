package com.films4you.req2;

import com.films4you.main.Database;
import com.films4you.main.RequirementInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Requirement: Find the top 10 most popular films based on
 * the number of rentals.

 * @author Joseph Sim
 *
 */
public class Requirement implements RequirementInterface {	
	
	public Film[] getFilms() throws SQLException, IllegalStateException {
	    Database db = new Database();
	    List<Film> allFilms = new ArrayList<>();
	    List<Payment> allPayments = new ArrayList<>();
	    List<Film> orderedFilms = new ArrayList<>();
	    Film[] top10Films = new Film[10];
	    
	 /*
	  *  Retrieve all the payments and puts them in a list, setting
	  *  the respective rentalID before putting it in the list
	  */
	    
	    ResultSet rs = db.query("SELECT * FROM payment");
	    if (rs == null) {
	    	throw new IllegalStateException("Query returned null");
	    }
	    
	    while (rs.next()) {
	    	int paymentID = rs.getInt(1);
	    	int rentalID = rs.getInt(4);
	    	if (paymentID >= 0 && rentalID >= 0) {
	    		Payment payment = new Payment(paymentID);
	    		payment.setRentalID(rentalID);
	    		allPayments.add(payment);
	    	}
	    }
	    
	 /*
	  * Retrieve all the inventoryIDs and matches them with the
	  * with the payments using the rentalIDs as the constant
	  */
	    rs = db.query("SELECT * FROM rental");
	    if (rs == null) {
	    	throw new IllegalStateException("Query returned null");
	    }
	    
	    while (rs.next()) {
	    	int rentalID = rs.getInt(1);
	    	int inventoryID = rs.getInt(2);
	    	if (inventoryID >= 0 && rentalID >= 0) {
	    		for (Payment payment : allPayments) {
	    			if (payment.getRentalID() == rentalID) {
	    				payment.setInventoryID(inventoryID);
	    			}
	    		}
	    	}
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
		    	if (inventoryID >= 0 && filmID >= 0) {
		    		for (Payment payment : allPayments) {
		    			if (payment.getInventoryID() == inventoryID) {
		    				payment.setFilmID(filmID);
		    			}
		    		}
		    	}
		    }
	    
	    // Retrieve all the films and puts them in a list
	    rs = db.query("SELECT * FROM film");
	    if (rs == null) {
	    	throw new IllegalStateException("Query returned null");
	    }
	    	
	    while (rs.next()) {
	    	int filmID = rs.getInt(1);
	    	String title = rs.getString(2);
	    	if (filmID >= 0 && title != null) {
	    		Film film = new Film(filmID);
	    		film.setTitle(title);
	    		allFilms.add(film);
	    	}
	    }
	    
	    // Cycles through both lists and updates the amount of times each film has
	    // been rented out
	    for (int j = 0; j < allPayments.size() - 1; j++) {
	    	for (int i = 0; i < allFilms.size() - 1; i++) {
	    		if (allFilms.get(i).getFilmID() == allPayments.get(j).getFilmID()) {
	    			allFilms.get(i).addRental();
	    		}
	    	}
	    }
	    
	    /*
	     * Loops 10 times, each time finding the highest rented film in the list of films
	     * and adding it to a list of 10 and then removing it from the list of films 
	     * before looping through again
	     */
	    Film tempMax;
	    int place = 0;
	    for (int i = 0; i < 10; i++) {
	    	for (int j = 0; j < allFilms.size() - i - 1; j++) {
	    		if (allFilms.get(j).getNumberOfRentals() < allFilms.get(j + 1).getNumberOfRentals()) {
	    			tempMax = allFilms.get(j);
	    			allFilms.set(j, allFilms.get(j + 1));
	    			allFilms.set(j + 1, tempMax);
	    		}
	    	}
	    	top10Films[place] = allFilms.get(i);
	    	place++;
	    }
	    return top10Films;
	    
	}
	
	/**
	 * A method which returns the top 10 rented films
	 
	 * @return A String containing the 10 top rented films each
	 * separated by commas in the format "[FILM]([FILMID]):[COUNT],  
	 * [FILM]([FILMID]):[COUNT], etc.".
	 */
  @Override
  public @Nullable String getValueAsString() {
	  String output = "";
	  Film[] top10Films;
	try {
		top10Films = getFilms();
		for (Film film : top10Films) {
			output += film.getTitle() + " (" + film.getFilmID()
					+ "): " + film.getNumberOfRentals() + "\n";
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
   * "1. [FILM]([FILMID]) has been rented [COUNT] times
   * 2. [FILM]([FILMID]) has been rented [COUNT] times
   * 3. etc.".
   */
  @Override
  public @NonNull String getHumanReadable() {
	  String output = "";
	  Film[] top10Films;
	  try {
		top10Films = getFilms();
		for (int i = 0; i < 10; i++) {
			output += (i+1) + ". " + top10Films[i].toString() + "\n";
		}
		return output;
	} 
	  catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
  }

}
