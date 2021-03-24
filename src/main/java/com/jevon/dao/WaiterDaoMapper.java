package com.jevon.dao;

import com.jevon.entity.User;
import com.jevon.entity.Waiter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface WaiterDaoMapper {

	//登录判断账号密码是否有效
	Waiter isValid(Map<String, Object> map) throws SQLException, IOException;

	//添加用户
	int addWaiter(Map<String, Object> map);

	Waiter selectByAccount(String account);

	//查询所有用户
	List<Waiter> getAllWaiter()throws SQLException, IOException;


	//根据ID删除用户
	int deleteByPrimaryKey(int id);

	//根据id查询用户
	Waiter selectByPrimaryKey(int id);


	//修改用户信息
	//  int updateByPrimaryKey(User user);
}
