package com.jevon.service;

import com.jevon.entity.AddressInfo;
import com.jevon.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface UserService {

	int isVaild(String phone,String password);
	
	int addUser(String phone,String password, String nick);
	
	User getUserById(int id)throws SQLException,IOException;
	
	User getUserByPhone(String phone);

	List<User> getAllUser();

	int createAddressInfo(int user_id,String address,String phone,String user_name,double latitude,double longitude);

	int updateAddressInfo(int id,String address,String phone,String user_name,double latitude,double longitude);

	List<AddressInfo> getAddressInfoList(int user_id);

	AddressInfo getLastUseAddressInfo(int user_id);
}
