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
    	assertEquals("ACADEMY DINOSAUR (1)\n"
    			+ "APACHE DIVINE (31): 8\n"
    			+ "BEVERLY OUTLAW (69): 8\n"
    			+ "BINGO TALENTED (73): 8\n"
    			+ "BOOGIE AMELIE (86): 8\n"
    			+ "BOUND CHEAPER (91): 8\n"
    			+ "BUCKET BROTHERHOOD (103): 8\n"
    			+ "BUTTERFLY CHOCOLAT (109): 8\n"
    			+ "CAT CONEHEADS (127): 8\n"
    			+ "CONFIDENTIAL INTERVIEW (174): 8\n", value);
    }
  }
  
  @Test
  public void testRequirementGetHumanReadable() {
    Requirement r = new Requirement();
    assertEquals("1. ACADEMY DINOSAUR (ID: 1) has been rented out 8 times\n"
    		+ "2. APACHE DIVINE (ID: 31) has been rented out 8 times\n"
    		+ "3. BEVERLY OUTLAW (ID: 69) has been rented out 8 times\n"
    		+ "4. BINGO TALENTED (ID: 73) has been rented out 8 times\n"
    		+ "5. BOOGIE AMELIE (ID: 86) has been rented out 8 times\n"
    		+ "6. BOUND CHEAPER (ID: 91) has been rented out 8 times\n"
    		+ "7. BUCKET BROTHERHOOD (ID: 103) has been rented out 8 times\n"
    		+ "8. BUTTERFLY CHOCOLAT (ID: 109) has been rented out 8 times\n"
    		+ "9. CAT CONEHEADS (ID: 127) has been rented out 8 times\n"
    		+ "10. CONFIDENTIAL INTERVIEW (ID: 174) has been rented out 8 times\n", r.getHumanReadable());
    
  }
  
 
}
