package com.jevon.service;

import com.jevon.entity.MedInOrder;
import com.jevon.entity.Order;
import com.jevon.entity.OrderDetail;

import java.util.List;


public interface OrderService {
	
	int addMedToCurOrder(int med_id,int user_id);
	
	List<MedInOrder> getMedListFromCurOrder(int user_id);
	
	int subMednumInOrder(int om_id,int user_id);
	
	int payNormalOrder(int user_id,int speed,String name,String phone,String address,int address_id);
	
	int postPrescriptOrder(int user_id,int speed,String name,String phone,String address,String picPath);

	List<Order> getWaitingOrderByTimeDesc();
	
	List<Order> getTakedOrder(int dist_id);
	
	List<Order> getFinishedOrder(int dist_id);
	
	OrderDetail getOrderDetailById(int order_id);
	
	int takeOrderByDistId(int dist_id,int order_id) ;
	
	int finishOrder(int order_id);
	
	List<Order> getStartingOrderByUserId(int user_id);
	
	List<Order> getFinishedOrderByUserId(int user_id);

	List<Order> getOrderList(int limit,int offset);

	int getOrderCount();
}
