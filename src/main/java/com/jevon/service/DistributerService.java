package com.jevon.service;

import com.jevon.entity.Distributer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public interface DistributerService {
	
	int isVaild(String phone,String password);
	
	int addDistributer(String phone,String password,String name,String id_no);
	
	Distributer getDistById(int id)throws SQLException,IOException;
	
	Distributer getDistByPhone(String phone);

	List<Distributer> getAllDistributer();

	int updateDistributerStatus(String id,String status);

	int deleteDistributer(String id);
}
