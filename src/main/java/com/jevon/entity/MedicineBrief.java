package com.jevon.entity;

public class MedicineBrief {

	private int id;
	private String name;
	private float price;
	private int prescript;
	private String note;
	private String pic;
	private int salesum;
	private int med_type;

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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getPrescript() {
		return prescript;
	}
	public void setPrescript(int prescript) {
		this.prescript = prescript;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getSalesum() {
		return salesum;
	}
	public void setSalesum(int salesum) {
		this.salesum = salesum;
	}
	public int getMed_type() {
		return med_type;
	}
	public void setMed_type(int med_type) {
		this.med_type = med_type;
	}
	
	

}
