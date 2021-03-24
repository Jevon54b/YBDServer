package com.jevon.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jevon.dao.MedDaoMapper;
import com.jevon.entity.MedType;
import com.jevon.entity.MedicineBrief;
import com.jevon.entity.MedicineDetail;
import com.jevon.service.MedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MedServiceImpl implements MedService {

	@Autowired
	MedDaoMapper medDaoMapper;
	
	@Override
	public List<MedicineBrief> getMedListByType(int t_id, int sort_way) {
		List<MedicineBrief> list;
		Map<String,Object> map = new HashMap<>();
		map.put("type_id", t_id);
		String sortString;
		switch (sort_way) {
		case 0:
			sortString = "id";
			break;
		case 1:
			sortString = "salesnum";
			break;
		case 2:
			sortString = "price desc";
			break;
		case 3:
			sortString = "price";
			break;
		default:
			sortString = "id";
		}
		map.put("sort_way", sortString);
		try {
			list = medDaoMapper.getMedListByTypeId(map);
		} catch (Exception e) {
			list=null;
		}
		return list;
	}

	@Override
	public MedicineDetail getMedDetailById(int m_id) {
		MedicineDetail medicineDetail;
		try {
			medicineDetail = medDaoMapper.getMedDetailById(m_id);
		} catch (Exception e) {
			medicineDetail = null;
		}
		return medicineDetail;
	}

	@Override
	public List<MedicineBrief> searchMedByKeyword(String keyword,int sort_way) {
		Map<String, Object> map = new HashMap<>();
		keyword = "%"+keyword+"%";
		map.put("key_word", keyword);
		String sortString;
		switch (sort_way) {
		case 0:
			sortString = "id";
			break;
		case 1:
			sortString = "salesnum";
			break;
		case 2:
			sortString = "price desc";
			break;
		case 3:
			sortString = "price";
			break;
		default:
			sortString = "id";
		}
		map.put("sort_way", sortString);
		List<MedicineBrief> list;
		try {
			list = medDaoMapper.selectMedByMatching(map);
		} catch (Exception e) {
			list = new ArrayList<MedicineBrief>();
		}
		return list;
	}

	@Override
	public List<MedicineBrief> getTopSalesnumMedList() {
		// TODO Auto-generated method stub
		return medDaoMapper.selectSalesNumTopNMed();
	}

	@Override
	public List<MedicineDetail> getAllMedicine(String limit,String offset) {
		Map<String,Object> map = new HashMap<>();
		int limitInt = Integer.valueOf(limit);
		int offsetInt = Integer.valueOf(offset);
		map.put("limit",limitInt);
		map.put("offset",offsetInt);
		return medDaoMapper.getAllMedicine(map);
	}

	@Override
	public int getMedicineCount() {
		return medDaoMapper.getMedicineCount().getCount();
	}

	@Override
	public int addMedicine(String name, float price, int prescript, String note, String pic, int med_type, String normal_name, String goods_name, String composition, String avoid, String function, String usage,
						   String properties, String packing_size, String adverse_reaction, String store_condition, String valid_time, String attensions, String register_number, String manufacturer) {
		Map<String,Object> map = new HashMap<>();
		map.put("name",name);
		map.put("price",price);
		map.put("prescript",prescript);
		map.put("note",note);
		map.put("function",function);
		map.put("pic",pic);
		map.put("med_type",med_type);
		map.put("normal_name",normal_name);
		map.put("goods_name",goods_name);
		map.put("composition",composition);
		map.put("avoid",avoid);
		map.put("usage",usage);
		map.put("properties",properties);
		map.put("packing_size",packing_size);
		map.put("adverse_reaction",adverse_reaction);
		map.put("store_condition",store_condition);
		map.put("valid_time",valid_time);
		map.put("attensions",attensions);
		map.put("register_number",register_number);
		map.put("manufacturer",manufacturer);
		return medDaoMapper.addMedicine(map);
	}

	@Override
	public int deleteMedicine(String id) {
		return medDaoMapper.deleteMedicine(id);
	}

	@Override
	public int updateMedicine(String med_id, String name, float price, int prescript, String note, String pic, int med_type, String normal_name, String goods_name, String composition, String avoid, String function, String usage,
							  String properties, String packing_size, String adverse_reaction, String store_condition, String valid_time, String attensions, String register_number, String manufacturer) {
		Map<String,Object> map = new HashMap<>();
		map.put("med_id",med_id);
		map.put("name",name);
		map.put("price",price);
		map.put("prescript",prescript);
		map.put("note",note);
		map.put("function",function);
		map.put("pic",pic);
		map.put("med_type",med_type);
		map.put("normal_name",normal_name);
		map.put("goods_name",goods_name);
		map.put("composition",composition);
		map.put("avoid",avoid);
		map.put("usage",usage);
		map.put("properties",properties);
		map.put("packing_size",packing_size);
		map.put("adverse_reaction",adverse_reaction);
		map.put("store_condition",store_condition);
		map.put("valid_time",valid_time);
		map.put("attensions",attensions);
		map.put("register_number",register_number);
		map.put("manufacturer",manufacturer);
		medDaoMapper.updateMedicine(map);
		return 0;
	}

	@Override
	public List<MedType> getAllMedType() {
		return medDaoMapper.getAllMedType();
	}

	@Override
	public int addMedType(String name) {
		return medDaoMapper.addMedType(name);
	}

	@Override
	public String getMedTypeByTypeId(String typeId) {
		return medDaoMapper.getMedTypeByTypeId(typeId);
	}


}
