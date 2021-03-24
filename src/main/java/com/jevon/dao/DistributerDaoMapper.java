package com.jevon.dao;

import com.jevon.entity.Distributer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;



public interface DistributerDaoMapper {

	//登录判断账号密码是否有效
	Distributer isValid(Map<String, Object> map) throws SQLException, IOException;

	//添加骑手
	int addDistributer(Map<String, Object> map);

	Distributer selectByPhone(String phone);

	//查询所有用户
	List<Distributer> getAllDistributer()throws SQLException, IOException;


	//根据ID删除用户
	int deleteByPrimaryKey(int id);

	//根据id查询用户
	Distributer selectByPrimaryKey(int id);

	int updateDistributerStatus(Map<String,String> map)throws SQLException, IOException;

	int deleteDistributer(String id)throws SQLException, IOException;
}
