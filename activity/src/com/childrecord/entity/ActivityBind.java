package com.childrecord.entity;

public class ActivityBind {
	private int id;
	private int person_id;
	private int activity_id;
	private int is_for;
	private int is_for_id;
	private int type;
	private String time;

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public int getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	public int getIs_for() {
		return is_for;
	}
	public void setIs_for(int is_for) {
		this.is_for = is_for;
	}
	public int getIs_for_id() {
		return is_for_id;
	}
	public void setIs_for_id(int is_for_id) {
		this.is_for_id = is_for_id;
	}
	
}
