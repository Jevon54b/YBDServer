package com.jevon.dao;

import com.jevon.entity.CountObject;
import com.jevon.entity.MedType;
import com.jevon.entity.MedicineBrief;
import com.jevon.entity.MedicineDetail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;



public interface MedDaoMapper {
	List<MedicineBrief> getMedListByTypeId(Map<String, Object> map)throws SQLException,IOException;
	
	MedicineDetail getMedDetailById(int m_id)throws SQLException,IOException;
	
	List<MedicineBrief> selectMedByMatching(Map<String, Object> map)throws SQLException,IOException;
	
	List<MedicineBrief> selectSalesNumTopNMed();

	List<MedicineDetail> getAllMedicine(Map<String,Object> map);

	CountObject getMedicineCount();

	int updateMedicine(Map<String,Object> map);

	int addMedicine(Map<String,Object> map);

	int deleteMedicine(String id);

	List<MedType> getAllMedType();

	int addMedType(String name);

	String getMedTypeByTypeId(String typeId);
}
