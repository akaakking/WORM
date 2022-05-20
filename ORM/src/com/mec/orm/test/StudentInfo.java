package com.mec.orm.test;

public class StudentInfo {
	String id;
	boolean sex;
	int age;
	String name;
	
	public StudentInfo() {
	}

	public String getId() {
		return id;
	}

	public boolean isSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("姓名：").append(name)
		  .append("\n年龄：").append(age)
		  .append("\n性别").append(sex ? "男" : "女")
		  .append("\n学号").append(id);
		
		return sb.toString();
	}
	
}
