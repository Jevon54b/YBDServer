package com.jevon.impl;

import com.jevon.dao.UserDaoMapper;
import com.jevon.dao.WaiterDaoMapper;
import com.jevon.entity.User;
import com.jevon.entity.Waiter;
import com.jevon.service.UserService;
import com.jevon.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Service
public class WaiterServiceImpl implements WaiterService {


	@Autowired
	WaiterDaoMapper waiterMapper;
	
	@Override
	public int isVaild(String account, String password){
		Map<String,Object> map = new HashMap<>();
		map.put("account", account);
		map.put("password", password);
		Waiter waiter;
		try {
			waiter = waiterMapper.isValid(map);
		} catch (Exception e) {
			e.printStackTrace();
			return 99;
		}
		if (waiter == null) {
			return 0;
		}
		return 1;
	}

	@Override
	public int addWaiter(String account, String password,  String name) {
		Map<String,Object> map = new HashMap<>();
		map.put("account", account);
		map.put("password", password);
		map.put("name", name);
		int result;
		try {
			result = waiterMapper.addWaiter(map);
		}catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	
	
	@Override
	public Waiter getWaiterById(int id) throws SQLException, IOException {
		Waiter waiter = waiterMapper.selectByPrimaryKey(id);
		return waiter;
	}

	@Override
	public Waiter getWaiterByAccount(String account) {
		Waiter waiter=waiterMapper.selectByAccount(account);
		return waiter;
	}
	
	
	

}
