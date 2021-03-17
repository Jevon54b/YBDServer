package com.jevon.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jevon.dao.MedDaoMapper;
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

	

}
