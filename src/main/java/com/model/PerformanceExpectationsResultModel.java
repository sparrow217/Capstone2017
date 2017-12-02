package com.model;

public class PerformanceExpectationsResultModel {

	private String PEID;
	private String performanceExpectation;
	private float percent;
	
	public String getPEID() {
		return PEID;
	}
	public void setPEID(String pEID) {
		PEID = pEID;
	}
	public String getPerformanceExpectation() {
		return performanceExpectation;
	}
	public void setPerformanceExpectation(String performanceExpectation) {
		this.performanceExpectation = performanceExpectation;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	
	
}
