package com.jevon.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jevon.dao.DistributerDaoMapper;
import com.jevon.entity.Distributer;
import com.jevon.service.DistributerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DistributerServiceImpl implements DistributerService {


	@Autowired
    DistributerDaoMapper distMapper;
	
	//1等待审核，2通过审核 -1 审核未通过 0用户名/密码错误
	@Override
	public int isVaild(String phone, String password){
		Map<String,Object> map = new HashMap<>();
		map.put("phone", phone);
		map.put("password", password);
		Distributer distributer;
		try {
			distributer = distMapper.isValid(map);
		} catch (Exception e) {
			e.printStackTrace();
			return 99;
		}
		if (distributer == null) {
			return 0;  //��ѯΪ��
		}
		switch (distributer.getIs_pass()) {
		case 0:
			return 1;
		case 1:
			return 2;
		case -1:
			return -1;
		default:
			return 0;
		}
		
	}

	@Override
	public int addDistributer(String phone, String password, String name, String id_no) {
		Map<String,Object> map = new HashMap<>();
		map.put("phone", phone);
		map.put("password", password);
		map.put("name", name);
		map.put("id_no", id_no);
		int result;
		try {
			result = distMapper.addDistributer(map);
		}catch (Exception e) {
			result = 0;
		}
		
		return result;
	}
	
	
	
	@Override
	public Distributer getDistById(int id) throws SQLException, IOException {
		Distributer distributer = distMapper.selectByPrimaryKey(id);
		return distributer;
	}

	@Override
	public Distributer getDistByPhone(String phone) {
		Distributer distributer=distMapper.selectByPhone(phone);
		return distributer;
	}

	@Override
	public List<Distributer> getAllDistributer() {
		try {
			return distMapper.getAllDistributer();
		}catch (Exception e){
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public int updateDistributerStatus(String id, String status) {
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("status",status);
		try {
			distMapper.updateDistributerStatus(map);
			return 1;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteDistributer(String id) {
		try {
			distMapper.deleteDistributer(id);
			return 1;
		}catch (DataIntegrityViolationException e){
			return -1;
		} catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}


}
