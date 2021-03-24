package com.jevon.service;

import com.jevon.entity.MedType;
import com.jevon.entity.MedicineBrief;
import com.jevon.entity.MedicineDetail;

import java.util.List;


public interface MedService {
	
	List<MedicineBrief> getMedListByType(int t_id, int sort_way);
	
	MedicineDetail getMedDetailById(int m_id);
	
	List<MedicineBrief> searchMedByKeyword(String keyword,int sort_way);
	
	List<MedicineBrief> getTopSalesnumMedList();

	List<MedicineDetail> getAllMedicine(String limit,String offset);

	int getMedicineCount();

	int addMedicine(String name, float price, int prescript, String note, String pic, int med_type, String normal_name, String goods_name, String composition, String avoid, String function,
					String usage, String properties, String packing_size, String adverse_reaction, String store_condition, String valid_time, String attensions, String register_number, String manufacturer);

	int deleteMedicine(String id);

	int updateMedicine(String med_id,String name, float price, int prescript, String note, String pic, int med_type, String normal_name, String goods_name, String composition, String avoid, String function,
					   String usage, String properties, String packing_size, String adverse_reaction, String store_condition, String valid_time, String attensions, String register_number, String manufacturer);

	List<MedType> getAllMedType();

	int addMedType(String name);

	String getMedTypeByTypeId(String typeId);
}
