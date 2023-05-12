package com.films4you.req4;

import com.films4you.main.Database;
import com.films4you.main.RequirementInterface;
import com.films4you.main.TaskNotAttemptedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Requirement: Find the most popular film in each category
 * based on the number of rentals

 * @author Joseph Sim
 *
 */
public class Requirement implements RequirementInterface {
	
	
	/**
	 * A private method which returns all Films ordered by the number of rentals
	 * each film has, from most rentals to least rentals
	 * 
	 * @return Array list of Films
	 * @throws SQLException on database error
	 * @throws IllegalStateException on error, e.g. when value is null when
	 * not allowed
	 */
	private List<Film> getFilms() throws SQLException, IllegalStateException {
		Database db = new Database();
		List<Film> allFilms = new ArrayList<>();
		List<Inventory> allItems = new ArrayList<>();
		List<Rental> allRentals = new ArrayList<>();
		
		/*
		 * Retrieves the film IDs and film names from the table "film"
		 * and stores them in the array list "allFilms"
		 */
		ResultSet rs = db.query("SELECT * FROM film");
		if (rs == null) {
			throw new IllegalStateException("Query returned null");
		}
		
		while (rs.next()) {
			Film film = new Film(rs.getInt(1), rs.getString(2));
			allFilms.add(film);
		}
		
		
		/*
		 * Retrieves the film IDs and inventory IDs from the table
		 * "inventory" and stores them in the array list "allItems"
		 */
		rs = db.query("SELECT * FROM inventory");
		if (rs == null) {
			throw new IllegalStateException("Query returned null");
		}
		
		while (rs.next()) {
			Inventory item = new Inventory(rs.getInt(1), rs.getInt(2));
			allItems.add(item);
		}
		
		
		/*
		 * Retrieves the rental IDs and inventory IDs from the table
		 * "rental" and stores them in the array list "allRentals"
		 */
		rs = db.query("SELECT * FROM rental");
		if (rs == null) {
			throw new IllegalStateException("Query returned null");
		}
		
		while (rs.next()) {
			Rental rental = new Rental(rs.getInt(1), rs.getInt(3));
			allRentals.add(rental);
		}
		
		db.close(); // Closes the database connection
		
		
		/*
		 * Cycles through the array lists allRentals, allItems, allFilms
		 * and updates each film's rental count based on the number of times
		 * which that film appears in the rental list
		 */
		for (Rental rental : allRentals) {
			for (Inventory item : allItems) {
				if (rental.getInventoryID() == item.getInventoryID()) {
					for (Film film : allFilms) {
						if (film.getFilmID() == item.getFilmID()) {
							film.addRental();
						}
					}
				}
			}
		}
		
		return sortFilms(allFilms);
		
	}
	
	/**
	 * A private method which sorts an array list of films based
	 * on each film's number of rentals from most rentals to least
	 * rentals
	 * 
	 * @param An array list: films
	 * @return An array list of Films
	 */
	private List<Film> sortFilms(List<Film> films) {
		int i, key, j;
		for (i = 1; i < films.size(); i++) {
			key = films.get(i).getRentals();
			j = i - 1;
			
			while (j >= 0 && films.get(j).getRentals() < key) {
				films.set(j + 1, films.get(j));
				j = j - 1;
			}
			films.set(j + 1, films.get(key));
		}
		return films;
	}
	
	
	/**
	 * A private method which returns the top film in each category
	 * 
	 * @return A hash map which links a category to the most rented film in that category
	 * @throws SQLException on database error
	 * @throws IllegalStateException on error, e.g. value null when not allowed
	 */
	private Map<Category, Film> getTopFilmsInCategory() throws IllegalStateException, SQLException {
		Database db = new Database();
		Map<Category, Film> topFilmInCategory = new HashMap<>();
		List<Film_Category> allFilmCategories = new ArrayList<>();
		List<Film> sortedFilms = getFilms();

		/*
		 * Retrieves the category IDs and category names from the table
		 * "category" and stores them in the array list "allCategories"
		 */
		ResultSet rs = db.query("SELECT * FROM category");
		if (rs == null) {
			throw new IllegalStateException("Query returned null");
		}
		
		while (rs.next()) {
			Category category = new Category(rs.getInt(1), rs.getString(2));
			topFilmInCategory.put(category, null);
		}
		
		
		/*
		 * Retrieves the film IDs and category IDs from the table
		 * "film_category" and stores them in the array list "allFilmCategories"
		 */
		rs = db.query("SELECT * FROM film_category");
		if (rs == null) {
			throw new IllegalStateException("Query returned null");
		}
		
		while (rs.next()) {
			Film_Category filmCategory = new Film_Category(rs.getInt(1), rs.getInt(2));
			allFilmCategories.add(filmCategory);
		}
		
		db.close();
		
		/*
		 * 
		 */
		for (Film film : sortedFilms) {
			for (Film_Category filmCategory : allFilmCategories) {
				if (film.getFilmID() == filmCategory.getFilmID()) {
					for (Category category : topFilmInCategory.keySet()) {
						if (filmCategory.getCategoryID() == category.getCategoryID()) {
							if (topFilmInCategory.get(category) != null) {
								topFilmInCategory.put(category, film);
							}
						}
					}
				}
			}
		}
		
		return topFilmInCategory;
	}
	
  
  @Override
  public @Nullable String getValueAsString() {
    throw new TaskNotAttemptedException();
  }

  @Override
  public @NonNull String getHumanReadable() {
    throw new TaskNotAttemptedException();
  }

}
