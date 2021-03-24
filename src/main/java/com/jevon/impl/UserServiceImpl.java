package com.jevon.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jevon.dao.UserDaoMapper;
import com.jevon.entity.AddressInfo;
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

	@Override
	public List<User> getAllUser() {
		try {
			return userMapper.getAllUser();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public int createAddressInfo(int user_id, String address, String phone, String user_name, double latitude, double longitude) {
		Map<String,Object> map = new HashMap<>();
		map.put("user_id",user_id);
		map.put("address",address);
		map.put("phone",phone);
		map.put("user_name",user_name);
		map.put("latitude",latitude);
		map.put("longitude",longitude);
		return userMapper.createAddressInfo(map);

	}

	@Override
	public int updateAddressInfo(int id, String address, String phone, String user_name, double latitude, double longitude) {
		Map<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("address",address);
		map.put("phone",phone);
		map.put("user_name",user_name);
		map.put("latitude",latitude);
		map.put("longitude",longitude);
		return userMapper.updateAddressInfo(map);
	}

	@Override
	public List<AddressInfo> getAddressInfoList(int user_id) {
		return userMapper.getAddressInfoList(user_id);
	}

	@Override
	public AddressInfo getLastUseAddressInfo(int user_id) {
		return userMapper.getLastUseAddressInfo(user_id);
	}


}
