package com.films4you.req2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class RequirementTest {
  
  @Test
  public void testRequirementGetActual() {
    Requirement r = new Requirement();
    String value = r.getValueAsString();
    
    if (value == null) {
    	fail("Requirement value was null");
    }	else {
    	assertEquals("BUCKET BROTHERHOOD (ID: 103): 34\n"
    			+ "ROCKETEER MOTHER (ID: 738): 33\n"
    			+ "FORWARD TEMPLE (ID: 331): 32\n"
    			+ "GRIT CLOCKWORK (ID: 382): 32\n"
    			+ "JUGGLER HARDLY (ID: 489): 32\n"
    			+ "RIDGEMONT SUBMARINE (ID: 730): 32\n"
    			+ "SCALAWAG DUCK (ID: 767): 32\n"
    			+ "APACHE DIVINE (ID: 31): 31\n"
    			+ "GOODFELLAS SALUTE (ID: 369): 31\n"
    			+ "HOBBIT ALIEN (ID: 418): 31\n", value);
    }
  }
  
  @Test
  public void testRequirementGetHumanReadable() {
    Requirement r = new Requirement();
    assertEquals("1. BUCKET BROTHERHOOD (ID: 103) has been rented out 34 times\n"
    		+ "2. ROCKETEER MOTHER (ID: 738) has been rented out 33 times\n"
    		+ "3. FORWARD TEMPLE (ID: 331) has been rented out 32 times\n"
    		+ "4. GRIT CLOCKWORK (ID: 382) has been rented out 32 times\n"
    		+ "5. JUGGLER HARDLY (ID: 489) has been rented out 32 times\n"
    		+ "6. RIDGEMONT SUBMARINE (ID: 730) has been rented out 32 times\n"
    		+ "7. SCALAWAG DUCK (ID: 767) has been rented out 32 times\n"
    		+ "8. APACHE DIVINE (ID: 31) has been rented out 31 times\n"
    		+ "9. GOODFELLAS SALUTE (ID: 369) has been rented out 31 times\n"
    		+ "10. HOBBIT ALIEN (ID: 418) has been rented out 31 times\n", r.getHumanReadable());
    
  } 
  
 
}
