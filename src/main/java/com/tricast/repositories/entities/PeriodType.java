package com.tricast.repositories.entities;

public enum PeriodType {
	NINTYMINUTES("90 minutes"),FIRSTHALF("First half"),SECONDHALF("Second half"),RACE("Rac");
	
	 private String value;    

	 private PeriodType(String s) {
	    this.value = s;
	 }

	  public String getValue() {
	    return value;
	  }

	  public String toString() {
		    return value;
	  }
	  
	
}
