package com.att.clientassignment.productinventory.model;

import java.util.List;


public class DisplayItems {
	
	private List<String> propertyNames;	
	
	public DisplayItems() {
		
	}

	public DisplayItems(List<String> propertyNames) {
		super();
		this.propertyNames = propertyNames;
	}

	public List<String> getPropertyNames() {
		return propertyNames;
	}

	public void setPropertyNames(List<String> propertyNames) {
		this.propertyNames = propertyNames;
	}

	@Override
	public String toString() {
		return "DisplayItems [propertyNames=" + propertyNames + "]";
	}		

}
