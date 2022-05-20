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
		sb.append("������").append(name)
		  .append("\n���䣺").append(age)
		  .append("\n�Ա�").append(sex ? "��" : "Ů")
		  .append("\nѧ��").append(id);
		
		return sb.toString();
	}
	
}
