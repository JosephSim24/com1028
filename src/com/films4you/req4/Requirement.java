package com.films4you.req4;

import com.films4you.main.Database;
import com.films4you.main.RequirementInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
	 * rentals, using bubble sort, and if the films have the same
	 * number of rentals, they are sorted alphabetically.
	 * 
	 * @param An array list: films
	 * @return An array list of Films
	 */
	private List<Film> sortFilms(List<Film> films) {
		Film tempFilm;
		for (int i = 0; i < films.size(); i++) {
			for (int j = 1; j < films.size() - i; j++) {
				if (films.get(j - 1).getRentals() < films.get(j).getRentals()) {
					tempFilm = films.get(j - 1);
					films.set(j - 1, films.get(j));
					films.set(j, tempFilm);
				}
			}
		}
		
		for (int k = 0; k < films.size(); k++) {
			for (int l = k + 1; l < films.size(); l++) {
				if (films.get(k).getRentals() == films.get(l).getRentals()) {
					if (films.get(k).getTitle().compareTo(films.get(l).getTitle()) > 0) {
						tempFilm = films.get(k);
						films.set(k, films.get(l));
						films.set(l, tempFilm);
					}
				}
			}
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
		Map<Category, Film> topFilmInCategory = new LinkedHashMap<>();
		//A linked hash map is used here to preserve the order of the categories
		
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
							if (topFilmInCategory.get(category) == null) {
								topFilmInCategory.replace(category, film);
							}
						}
					}
				}
			}
		}
		
		return topFilmInCategory;
	}
	
	
	/**
	 * A method which returns each category and their respective most
	 * rented film in a non-human-readable format.
	 * 
	 * @return A String containing a film category with that category's
	 * most popular film in the format "[CATEGORYNAME]:[FILMNAME]:[FILMID]:[RENTCOUNT]:\n
	 * [CATEGORYNAME]:[FILMNAME]:[FILMID]:[RENTCOUNT]:\n etc.".
	 */
	@Override
	public @Nullable String getValueAsString() {
		try {
			Map<Category, Film> filmCategory = getTopFilmsInCategory();
			String output = "";
			for (Category category : filmCategory.keySet()) {
				if (filmCategory.get(category) != null) {
					output += category.getName() + ":" + 
							filmCategory.get(category).toString() + ":\n";
				} else {
					output += category.getName() + ":0:0:\n"; 
				}
			}
			return output;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * A method to parse the String from method getValueAsString and
	 * return it in a human-readable-format.
	 * 
	 * @return A String formatted for the end user containing the most
	 * popular film in each category. In the format
	 * "The most popular films for each category are:\n
	 * [CATEGORYNAME]: [FILMNAME](ID: [FILMID]) which has been rented [RENTCOUNT] times\n
	 * [CATEGORYNAME]: [FILMNAME](ID: [FILMID]) which has been rented [RENTCOUNT] times\n
	 * etc.
	 */
	@Override
	public @NonNull String getHumanReadable() {
		String valueAsString = getValueAsString();
		if (valueAsString == null) {
			  return "No results found or an error has occurred";
		  }
		String output = "The most popular film in each category is:\n";
		for (int s = 0; s < valueAsString.lines().count()*4; s = s+4) {
			output += valueAsString.split(":")[s] + ": ";
			output += valueAsString.split(":")[s + 1] + " (ID: ";
			output += valueAsString.split(":")[s + 2] + ") which has been rented ";
			output += valueAsString.split(":")[s + 3] + " times";
		}
		return output;
	}

}
