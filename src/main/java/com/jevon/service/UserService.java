package com.jevon.service;

import com.jevon.entity.User;

import java.io.IOException;
import java.sql.SQLException;


public interface UserService {
	//�ж��û���¼״̬��0Ϊ�˺Ż��������1Ϊ�˺Ż�������ȷ
	int isVaild(String phone,String password);
	
	int addUser(String phone,String password, String nick);
	
	User getUserById(int id)throws SQLException,IOException;
	
	User getUserByPhone(String phone);
}
