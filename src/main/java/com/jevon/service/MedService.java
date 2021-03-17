package com.jevon.service;

import com.jevon.entity.MedicineBrief;
import com.jevon.entity.MedicineDetail;

import java.util.List;


public interface MedService {
	
	List<MedicineBrief> getMedListByType(int t_id, int sort_way);
	
	MedicineDetail getMedDetailById(int m_id);
	
	List<MedicineBrief> searchMedByKeyword(String keyword,int sort_way);
	
	List<MedicineBrief> getTopSalesnumMedList();
}
