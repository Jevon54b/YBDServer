package com.jevon.entity;

import java.util.List;

public class OrderDetail {

	private int id;
	private String user_name;
	private String user_phone;
	private String user_address;
	private String store_address;
	private float total_sum;
	private int speed;
	private List<MedInOrder> medList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getStore_address() {
		return store_address;
	}
	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}
	public float getTotal_sum() {
		return total_sum;
	}
	public void setTotal_sum(float total_sum) {
		this.total_sum = total_sum;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public List<MedInOrder> getMedList() {
		return medList;
	}
	public void setMedList(List<MedInOrder> medList) {
		this.medList = medList;
	}
	
	

}
