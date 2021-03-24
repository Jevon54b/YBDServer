package com.jevon.entity;

public class MedicineDetail {

	private int id;
	private String name;
	private float price;
	private int prescript;
	private String note;
	private String pic;
	private int salesnum;
	private int med_type;
	private String type_name;
	private String normal_name;
	private String goods_name;
	private String composition;
	private String avoid;
	private String function;
	private String usage;
	private String properties;
	private String packing_size;
	private String adverse_reaction;
	private String store_condition;
	private String valid_time;
	private String attensions;
	private String register_number;
	private String manufacturer;



	private String getTypeNameFromId(int med_type) {
		String title;
		switch(Integer.toString(med_type)){
			case "30001":
				title = "感冒发烧";
				break;
			case "30002":
				title = "两性健康";
				break;
			case "30003":
				title = "咳嗽用药";
				break;
			case "30004":
				title = "皮肤用药";
				break;
			case "30005":
				title = "肠胃用药";
				break;
			case "30006":
				title = "跌打扭伤";
				break;
			case "30007":
				title = "儿童用药";
				break;
			case "30008":
				title = "滋补调养";
				break;
			case "30009":
				title = "处方用药";
				break;
			case "30010":
				title = "家庭常备";
				break;
			case "30011":
				title = "五官用药";
				break;
			case "30012":
				title = "维矿补益";
				break;
			default:
				title="???";
		}
		return title;
	}
		
	
	
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
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

	public int getSalesnum() {
		return salesnum;
	}

	public void setSalesnum(int salesnum) {
		this.salesnum = salesnum;
	}

	public int getMed_type() {
		return med_type;
	}
	public void setMed_type(int med_type) {
		this.med_type = med_type;
		this.type_name=getTypeNameFromId(med_type);
	}
	public String getNormal_name() {
		return normal_name;
	}
	public void setNormal_name(String normal_name) {
		this.normal_name = normal_name;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public String getAvoid() {
		return avoid;
	}
	public void setAvoid(String avoid) {
		this.avoid = avoid;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getPacking_size() {
		return packing_size;
	}
	public void setPacking_size(String packing_size) {
		this.packing_size = packing_size;
	}
	public String getAdverse_reaction() {
		return adverse_reaction;
	}
	public void setAdverse_reaction(String adverse_reaction) {
		this.adverse_reaction = adverse_reaction;
	}
	public String getStore_condition() {
		return store_condition;
	}
	public void setStore_condition(String store_condition) {
		this.store_condition = store_condition;
	}
	public String getValid_time() {
		return valid_time;
	}
	public void setValid_time(String valid_time) {
		this.valid_time = valid_time;
	}
	public String getAttensions() {
		return attensions;
	}
	public void setAttensions(String attensions) {
		this.attensions = attensions;
	}
	public String getRegister_number() {
		return register_number;
	}
	public void setRegister_number(String register_number) {
		this.register_number = register_number;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}
