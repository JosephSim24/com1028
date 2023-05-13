package com.films4you.req4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RequirementTest {
  
  /**
   * A test to check that the method "getValueAsString" works
   * correctly and returns the correct String
   */
  @Test
  public void testRequirementGetActual() {
    Requirement r = new Requirement();
    assertEquals("Action:RUGRATS SHAKESPEARE:748:30:\n"
    		+ "Animation:JUGGLER HARDLY:489:32:\n"
    		+ "Children:ROBBERS JOON:735:31:\n"
    		+ "Classics:TIMBERLAND SKY:891:31:\n"
    		+ "Comedy:ZORRO ARK:1000:31:\n"
    		+ "Documentary:WIFE TURN:973:31:\n"
    		+ "Drama:HOBBIT ALIEN:418:31:\n"
    		+ "Family:APACHE DIVINE:31:31:\n"
    		+ "Foreign:ROCKETEER MOTHER:738:33:\n"
    		+ "Games:FORWARD TEMPLE:331:32:\n"
    		+ "Horror:PULP BEVERLY:702:30:\n"
    		+ "Music:SCALAWAG DUCK:767:32:\n"
    		+ "New:RIDGEMONT SUBMARINE:730:32:\n"
    		+ "Sci-Fi:GOODFELLAS SALUTE:369:31:\n"
    		+ "Sports:GLEAMING JAWBREAKER:361:29:\n"
    		+ "Travel:BUCKET BROTHERHOOD:103:34:\n", r.getValueAsString());
  }
  
  /**
   * A test to check that the method "getHumanReadable" works correctly
   * and returns the correct readable String
   */
  @Test
  public void testGetHumanReadable() {
	  Requirement r = new Requirement();
	  assertEquals("The most popular film in each category is:\n"
	  		+ "Action: RUGRATS SHAKESPEARE (ID: 748) which has been rented 30 times\n"
	  		+ "Animation: JUGGLER HARDLY (ID: 489) which has been rented 32 times\n"
	  		+ "Children: ROBBERS JOON (ID: 735) which has been rented 31 times\n"
	  		+ "Classics: TIMBERLAND SKY (ID: 891) which has been rented 31 times\n"
	  		+ "Comedy: ZORRO ARK (ID: 1000) which has been rented 31 times\n"
	  		+ "Documentary: WIFE TURN (ID: 973) which has been rented 31 times\n"
	  		+ "Drama: HOBBIT ALIEN (ID: 418) which has been rented 31 times\n"
	  		+ "Family: APACHE DIVINE (ID: 31) which has been rented 31 times\n"
	  		+ "Foreign: ROCKETEER MOTHER (ID: 738) which has been rented 33 times\n"
	  		+ "Games: FORWARD TEMPLE (ID: 331) which has been rented 32 times\n"
	  		+ "Horror: PULP BEVERLY (ID: 702) which has been rented 30 times\n"
	  		+ "Music: SCALAWAG DUCK (ID: 767) which has been rented 32 times\n"
	  		+ "New: RIDGEMONT SUBMARINE (ID: 730) which has been rented 32 times\n"
	  		+ "Sci-Fi: GOODFELLAS SALUTE (ID: 369) which has been rented 31 times\n"
	  		+ "Sports: GLEAMING JAWBREAKER (ID: 361) which has been rented 29 times\n"
	  		+ "Travel: BUCKET BROTHERHOOD (ID: 103) which has been rented 34 times", r.getHumanReadable());
  }
 
}
