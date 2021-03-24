package com.jevon.service;

import com.jevon.entity.User;
import com.jevon.entity.Waiter;

import java.io.IOException;
import java.sql.SQLException;


public interface WaiterService {

	int isVaild(String account,String password);
	
	int addWaiter(String account,String password, String name);
	
	Waiter getWaiterById(int id)throws SQLException,IOException;

	Waiter getWaiterByAccount(String account);
}
