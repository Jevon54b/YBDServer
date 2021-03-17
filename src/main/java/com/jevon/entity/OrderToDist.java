package com.jevon.entity;

import java.sql.Timestamp;

public class OrderToDist {
	private Timestamp pay_time;
	private Timestamp finish_time;
	private float total_sum;
	private int order_id;
	private String user_address;
	private String store_address;
	private int speed;
	
	public Timestamp getPay_time() {
		return pay_time;
	}
	public void setPay_time(Timestamp pay_time) {
		this.pay_time = pay_time;
	}
	public float getTotal_sum() {
		return total_sum;
	}
	public void setTotal_sum(float total_sum) {
		this.total_sum = total_sum;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
