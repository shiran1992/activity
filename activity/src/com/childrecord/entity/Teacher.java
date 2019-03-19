package com.childrecord.entity;

public class Teacher {
	private int id;
	private String name;
	private int age;
	private String sex;
	private String zchen;
	private String idnum;
	private String major;
	private String academicdegree;
	private String phone;
	private String email;
	private String idcard;
	private String unit;
	private String password;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getZchen() {
		return zchen;
	}
	public void setZchen(String zchen) {
		this.zchen = zchen;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getAcademicdegree() {
		return academicdegree;
	}
	public void setAcademicdegree(String academicdegree) {
		this.academicdegree = academicdegree;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", age=" + age
				+ ", sex=" + sex + ", zchen=" + zchen + ", idnum=" + idnum
				+ ", major=" + major + ", academicdegree=" + academicdegree
				+ ", phone=" + phone + ", email=" + email + ", idcard="
				+ idcard + ", unit=" + unit + ", password=" + password + "]";
	}
	
}
