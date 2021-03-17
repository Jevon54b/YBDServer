package com.jevon.entity;

import java.security.KeyStore.PrivateKeyEntry;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Order {

	private int id;
	private float total_sum;
	private String create_time;
	private String pay_time;
	private int user_id;
	private int distributer_id;
	private int state;
	private String user_name;
	private String user_phone;
	private String user_address;
	private String store_address;
	private int speed;
	private int need_check;
	private List<MedInOrder> medList;
	
	
	
	public int getNeed_check() {
		return need_check;
	}
	public void setNeed_check(int need_check) {
		this.need_check = need_check;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(Timestamp pay_time) {
		this.pay_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pay_time);
	}
	public List<MedInOrder> getMedList() {
		return medList;
	}
	public void setMedList(List<MedInOrder> medList) {
		this.medList = medList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getTotal_sum() {
		return total_sum;
	}
	public void setTotal_sum(float total_sum) {
		this.total_sum = total_sum;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(create_time);
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getDistributer_id() {
		return distributer_id;
	}
	public void setDistributer_id(int distributer_id) {
		this.distributer_id = distributer_id;
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
	
	

}
