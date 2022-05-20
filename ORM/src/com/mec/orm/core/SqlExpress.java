package com.mec.orm.core;


public class SqlExpress {
	public static String select(ClassTableDefination classTableDefination) {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(classTableDefination.getTableName());		
		
		return sb.toString();
	}
	
	public static String selectById(String keyWord,ClassTableDefination classTableDefination) {
		StringBuffer sb = new StringBuffer(select(classTableDefination));
		sb.append(" WHERE ")
		  .append(classTableDefination.getId().getColumn())
		  .append(" = ")
		  .append(keyWord);
		return sb.toString(); 
		
	}
	
	
		
		
	

}

