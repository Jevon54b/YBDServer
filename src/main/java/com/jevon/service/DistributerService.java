package com.jevon.service;

import com.jevon.entity.Distributer;

import java.io.IOException;
import java.sql.SQLException;



public interface DistributerService {
	
	//�ж����ֵ�¼״̬��0Ϊ�˺Ż��������1Ϊ�ȴ���ˣ�2Ϊͨ����ˣ�-1Ϊ��˲�ͨ��
	int isVaild(String phone,String password);
	
	int addDistributer(String phone,String password,String name,String id_no);
	
	Distributer getDistById(int id)throws SQLException,IOException;
	
	Distributer getDistByPhone(String phone);
}
