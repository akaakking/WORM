package com.mec.orm.core;

import java.util.ArrayList;
import java.util.List;

import com.mec.util.TypeParser;

/*
 * �������ֻ��һ������һ��klass��Ϳ��Եõ�������¼�Ķ���
 */

public class ClassTableDefination {
	private Class<?> klass;
	private String tableName;
	private List<PropertyColumn> propertyColumns;
	private PropertyColumn id;
	
	public ClassTableDefination() {
		this.propertyColumns = new ArrayList<PropertyColumn>();
	}
	
	public void addPropertyColumns(PropertyColumn propertyColumn) {
		this.propertyColumns.add(propertyColumn);
	}
	
	public void setKlass(Class<?> klass) {
		this.klass = klass;
	}

	public List<PropertyColumn> getPropertyColumns() {
		return propertyColumns;
	}
	
	
	public void setKlass(String className) throws ClassNotFoundException {
		this.klass = TypeParser.toType(className);
	}
	
	public Class<?> getKlass() {
		return klass;
	}
	
	public PropertyColumn getId() {
		return id;
	}
	
	public void setId(PropertyColumn id) {
		this.id = id;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	@Override
		public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("��").append(tableName).append("\n")
		  .append("\n�ࣺ").append(klass.getName()).append("\n")//ע��
		  .append("\nid <=> ").append(id).append("\n");
		for(PropertyColumn propertyColumn :propertyColumns) {
			sb.append(propertyColumn).append("\n");
		}
		return sb.toString();
		}
}
