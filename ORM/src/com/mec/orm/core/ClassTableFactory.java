package com.mec.orm.core;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.mec.util.XMLParser;

public class ClassTableFactory {
	private static final Map<Class<?>, ClassTableDefination> classTablePool;
	
	static {
		classTablePool = new HashMap<Class<?>, ClassTableDefination>();
	}
	
	public ClassTableFactory() {
	}
	
	public static ClassTableDefination getClassTableDefination(Class<?> klass) {
		return classTablePool.get(klass);
	}
	
	public static void scanMapping(String xmlPath) {
		try {
			new XMLParser() {
				
				@Override
				public void parserElement(Element element) {
					ClassTableDefination classTableDefination = new ClassTableDefination();
					String klassName = element.getAttribute("name");
					String tableName = element.getAttribute("tableName");
					try {
						classTableDefination.setTableName(tableName);
						classTableDefination.setKlass(klassName);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					new XMLParser() {
						
						@Override
						public void parserElement(Element element) {
							String columnName = element.getAttribute("columnName");
							String propertyName = element.getAttribute("name");
							
							try {
								Field property =  classTableDefination.getKlass().getDeclaredField(propertyName);
								PropertyColumn propertyColumn = new PropertyColumn(columnName,property);
								classTableDefination.addPropertyColumns(propertyColumn);
							} catch (NoSuchFieldException e) {
								e.printStackTrace();
							} catch (SecurityException e) {
								e.printStackTrace();
							}
							
						}
					}.parseTag(element,"property");
					
					new XMLParser() {
						
						@Override
						public void parserElement(Element element) {
							String propertyName =  element.getAttribute("propertyName");
							String column = element.getAttribute("columnName");
							
							try {
								Field property = classTableDefination.getClass().getDeclaredField(propertyName);
								classTableDefination.setId(new PropertyColumn(column,property));
								
							} catch (NoSuchFieldException e) {
							} catch (SecurityException e) {
							}
							
						}
					}.parseTag(element, "id");
					
					classTablePool.put(classTableDefination.getKlass(), classTableDefination);
				}
				
				
			}.parseTag(XMLParser.newDocument(xmlPath), "class");
		} catch (SAXException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
