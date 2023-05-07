package com.films4you.req3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class RequirementTest {
  
  /**
   * A test to check that the method getValueAsString works
   */
  @Test
  public void testRequirementGetActual() {
    Requirement r = new Requirement();
    String value = r.getValueAsString();
    
    if (value == null) {
    	fail("Requirement value was null");
    } else {
    	assertEquals("4:" + 
    			"PENELOPE:GUINESS:1:\n" +
    			"PENELOPE:PINKETT:54:\n" +
    			"PENELOPE:CRONYN:104:\n" +
    			"PENELOPE:MONROE:120:\n", value);
    }
  }
  
  /**
   * A test to check that the method getHumanReadable works
   */
  @Test
  public void testRequirementGetHumanReadable() {
	  Requirement r = new Requirement();
	  assertEquals("There are 4 actors that have the first name Penelope:\n" +
			  "PENELOPE GUINESS (ID: 1)\n" +
			  "PENELOPE PINKETT (ID: 54)\n" +
			  "PENELOPE CRONYN (ID: 104)\n" +
			  "PENELOPE MONROE (ID: 120)", r.getHumanReadable());
  }
 
}
