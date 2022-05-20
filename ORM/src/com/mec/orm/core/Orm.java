package com.mec.orm.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mec.util.PropertiesParser;

/*
 * 类表映射
 * 类和表的名字是一个
 * 
 */

public class Orm {
	private static Connection connection;
	
	public Orm() {
	}
	
	
	public static void connectToDataBase(String propertiesPath) {
		PropertiesParser.scanProperties(propertiesPath);
		String user = PropertiesParser.getValue("user");
		String Driver = PropertiesParser.getValue("Driver");
		String password = PropertiesParser.getValue("password");
		String url = PropertiesParser.getValue("url");
		
		try {
			Class.forName(Driver);
			connection = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(Class<?> klass,String keyWord) throws InstantiationException
																, IllegalAccessException
																, IllegalArgumentException
																, InvocationTargetException
																, NoSuchMethodException
																, SecurityException, SQLException {
		ClassTableDefination classTableDefination = ClassTableFactory.getClassTableDefination(klass);
		Object object = klass.getConstructor().newInstance();
		String sqlString = SqlExpress.selectById(keyWord, classTableDefination);
		
		PreparedStatement statement = connection.prepareStatement(sqlString);
		
		ResultSet rs = statement.executeQuery(sqlString);
		
		if (rs.next()) {
			fillObject(classTableDefination, object,rs);
		}
		
		 
		return (T)object;
	}
	
	
	private static void fillObject(ClassTableDefination classTableDefination
							,Object object,ResultSet rs) throws SQLException, IllegalArgumentException, IllegalAccessException {
		for (PropertyColumn propertyColumn : classTableDefination.getPropertyColumns()) {
			Field field = propertyColumn.getProperty();
			String column = propertyColumn.getColumn();
			
			field.setAccessible(true);
			field.set(object, rs.getObject(column));
		}
	}
	
}
