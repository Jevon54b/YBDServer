package com.jevon.service;

import com.jevon.entity.Distributer;

import java.io.IOException;
import java.sql.SQLException;



public interface DistributerService {
	
	//判断骑手登录状态，0为账号或密码错误，1为等待审核，2为通过审核，-1为审核不通过
	int isVaild(String phone,String password);
	
	int addDistributer(String phone,String password,String name,String id_no);
	
	Distributer getDistById(int id)throws SQLException,IOException;
	
	Distributer getDistByPhone(String phone);
}
