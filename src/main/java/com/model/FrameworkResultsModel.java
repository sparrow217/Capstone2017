package com.model;

public class FrameworkResultsModel{
	private int id;
	private String dimension;
	private String frameworkElement;
	private String frameworkSubelement;
	public float percent;
	
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getFrameworkElement() {
		return frameworkElement;
	}
	public void setFrameworkElement(String frameworkElement) {
		this.frameworkElement = frameworkElement;
	}
	public String getFrameworkSubelement() {
		return frameworkSubelement;
	}
	public void setFrameworkSubelement(String frameworkSubelement) {
		this.frameworkSubelement = frameworkSubelement;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	
}
