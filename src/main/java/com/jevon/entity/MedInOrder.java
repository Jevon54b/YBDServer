package com.jevon.entity;

public class MedInOrder {

	private int med_id;
	private int om_id;
	private String name;
	private String pic;
	private int prescript;
	private String packing_size;
	private int med_num;
	private float price;
	private float total_sum;
	
	
	
	
	public int getOm_id() {
		return om_id;
	}
	public void setOm_id(int om_id) {
		this.om_id = om_id;
	}
	public int getMed_id() {
		return med_id;
	}
	public void setMed_id(int med_id) {
		this.med_id = med_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getPrescript() {
		return prescript;
	}
	public void setPrescript(int prescript) {
		this.prescript = prescript;
	}
	public String getPacking_size() {
		return packing_size;
	}
	public void setPacking_size(String packing_size) {
		this.packing_size = packing_size;
	}
	public int getMed_num() {
		return med_num;
	}
	public void setMed_num(int med_num) {
		this.med_num = med_num;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getTotal_sum() {
		return total_sum;
	}
	public void setTotal_sum(float total_sum) {
		this.total_sum = total_sum;
	}
	
	

}
