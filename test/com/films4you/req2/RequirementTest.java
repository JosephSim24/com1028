package com.films4you.req2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class RequirementTest {
  
	/**
	 * A test to check that getValueAsString works
	 */
  @Test
  public void testRequirementGetActual() {
    Requirement r = new Requirement();
    String value = r.getValueAsString();
    
    if (value == null) {
    	fail("Requirement value was null");
    }	else {
    	assertEquals("1:BUCKET BROTHERHOOD:103:34:\n"
    			+ "2:ROCKETEER MOTHER:738:33:\n"
    			+ "3:FORWARD TEMPLE:331:32:\n"
    			+ "4:GRIT CLOCKWORK:382:32:\n"
    			+ "5:JUGGLER HARDLY:489:32:\n"
    			+ "6:RIDGEMONT SUBMARINE:730:32:\n"
    			+ "7:SCALAWAG DUCK:767:32:\n"
    			+ "8:APACHE DIVINE:31:31:\n"
    			+ "9:GOODFELLAS SALUTE:369:31:\n"
    			+ "10:HOBBIT ALIEN:418:31:\n", value);
    }
  }
  
  /**
   * A test to check that getHumanReadable works
   */
  @Test
  public void testRequirementGetHumanReadable() {
    Requirement r = new Requirement();
    assertEquals("The top 10 films based on their number of rentals is:\n"
    		+ "1. BUCKET BROTHERHOOD (ID: 103) has been rented 34 times\n"
    		+ "2. ROCKETEER MOTHER (ID: 738) has been rented 33 times\n"
    		+ "3. FORWARD TEMPLE (ID: 331) has been rented 32 times\n"
    		+ "4. GRIT CLOCKWORK (ID: 382) has been rented 32 times\n"
    		+ "5. JUGGLER HARDLY (ID: 489) has been rented 32 times\n"
    		+ "6. RIDGEMONT SUBMARINE (ID: 730) has been rented 32 times\n"
    		+ "7. SCALAWAG DUCK (ID: 767) has been rented 32 times\n"
    		+ "8. APACHE DIVINE (ID: 31) has been rented 31 times\n"
    		+ "9. GOODFELLAS SALUTE (ID: 369) has been rented 31 times\n"
    		+ "10. HOBBIT ALIEN (ID: 418) has been rented 31 times", r.getHumanReadable());
    
  } 
  
 
}
