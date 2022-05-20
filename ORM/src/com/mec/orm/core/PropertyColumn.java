package com.mec.orm.core;

import java.lang.reflect.Field;

public class PropertyColumn {
	private String column;
	private Field property;
	
	public PropertyColumn() {
	}
	
	public  PropertyColumn(String column,Field property) {
		this.column = column;
		this.property = property;
	}
	

	
	public void setColumn(String column) {
		this.column = column;
	}
	
	public String getColumn() {
		return column;
	}
	
	public void setProperty(Field property) {
		this.property = property;
	}
	
	public Field getProperty() {
		return property;
	}
	
	@Override
	public String toString() {
		return "column:" + column
				+ "  field:" + property.getName();
	}
}
