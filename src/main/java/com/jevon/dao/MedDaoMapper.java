package com.jevon.dao;

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
}
