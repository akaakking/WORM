package com.mec.orm.test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.mec.orm.core.ClassTableFactory;
import com.mec.orm.core.Orm;

public class Demo {
	public static void main(String[] args) {
		ClassTableFactory.scanMapping("/classtablemapping.xml");
		Orm.connectToDataBase("/database.config.properties");
		try {
			StudentInfo st = Orm.get(StudentInfo.class, "03201111");
			System.out.println(st);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
