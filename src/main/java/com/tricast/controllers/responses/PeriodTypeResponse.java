package com.tricast.controllers.responses;

import java.io.Serializable;

public class PeriodTypeResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6366007905616529333L;

	private String periodTypeDescription;

	public String getPeriodTypeDescription() {
		return periodTypeDescription;
	}

	public void setPeriodTypeDescription(String periodTypeDescription) {
		this.periodTypeDescription = periodTypeDescription;
	}
    
    
}
