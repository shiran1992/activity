package com.childrecord.entity;

import java.util.Date;

public class Activity {
	private int id;
	private int teacherid;
	public int getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	private String name;
	private String location;
	private String start_time;
	private String end_time;
	private String endsign_time;
	private int max_people;
	private int free;
	private int pay_money;
	private String image_top;
	private String image_middle;
	private String image_under;
	private String is_recommend;
	private String is_hot;
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(String is_hot) {
		this.is_hot = is_hot;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getEndsign_time() {
		return endsign_time;
	}
	public void setEndsign_time(String endsign_time) {
		this.endsign_time = endsign_time;
	}
	public int getMax_people() {
		return max_people;
	}
	public void setMax_people(int max_people) {
		this.max_people = max_people;
	}
	public int getFree() {
		return free;
	}
	public void setFree(int free) {
		this.free = free;
	}
	public int getPay_money() {
		return pay_money;
	}
	public void setPay_money(int pay_money) {
		this.pay_money = pay_money;
	}
	public String getImage_top() {
		return image_top;
	}
	public void setImage_top(String image_top) {
		this.image_top = image_top;
	}
	public String getImage_middle() {
		return image_middle;
	}
	public void setImage_middle(String image_middle) {
		this.image_middle = image_middle;
	}
	public String getImage_under() {
		return image_under;
	}
	public void setImage_under(String image_under) {
		this.image_under = image_under;
	}
	public String getIs_recommend() {
		return is_recommend;
	}
	public void setIs_recommend(String is_recommend) {
		this.is_recommend = is_recommend;
	}
}
