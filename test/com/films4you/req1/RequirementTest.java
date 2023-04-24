package com.films4you.req1;

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
    	assertEquals("200", value);
    }
  }
 
  @Test
  public void testRequirementGetHumanReadable() {
    Requirement r = new Requirement();
    assertEquals("The total number of actors is 200",
    		r.getHumanReadable());
  }
  
}
