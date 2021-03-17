package com.jevon.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.jevon.dao.UserDaoMapper;
import com.jevon.entity.User;
import com.jevon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {


	@Autowired
	UserDaoMapper userMapper;
	
	@Override
	public int isVaild(String phone, String password){
		Map<String,Object> map = new HashMap<>();
		map.put("phone", phone);
		map.put("password", password);
		User user;
		try {
			user = userMapper.isValid(map);
		} catch (Exception e) {
			e.printStackTrace();
			return 99;
		}
		if (user == null) {
			return 0;
		}
		return 1;
	}

	@Override
	public int addUser(String phone, String password,  String nick) {
		Map<String,Object> map = new HashMap<>();
		map.put("phone", phone);
		map.put("password", password);
		map.put("nick", nick);
		int result;
		try {
			result = userMapper.addUser(map);
		}catch (Exception e) {
			result = 0;
		}
		return result;
	}
	
	
	
	@Override
	public User getUserById(int id) throws SQLException, IOException {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public User getUserByPhone(String phone) {
		User user=userMapper.selectByPhone(phone);
		return user;
	}
	
	
	

}
