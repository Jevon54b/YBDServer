package com.jevon.dao;

import com.jevon.entity.MedInOrder;
import com.jevon.entity.Order;
import com.jevon.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;



public interface OrderDaoMapper {

	Order getCurrentOrderByUserId(int user_id);//获取当前订单

	int createNewCurOrder(int user_id)throws Exception;//如果不存在则新建当前订单

	Integer matchOrderMed(Map<String,Object> map); //根据 med_id 与 order_id 查询是否已有记录，如果有则更新med_num

	int addMedToOrder(Map<String, Object> map)throws Exception; //将药品加入当前订单

	int addMednum(int om_id)throws Exception;  //药品num+1

	int subMednum(int om_id)throws Exception;  //药品num-1

	int deleteMedOrder(int om_id)throws Exception; //删除om记录 表示从当前订单移除该药品

	Integer getCurMednumByOMId(int om_id); //获取当前药品购买数量

	Float getOrderTotalSum(int order_id); //获取当前订单总额

	int updateSumToOrder(Map<String,Object> map)throws Exception;  //更新当前订单总额

	List<MedInOrder> getMedListFromCurOrder(int user_id)throws Exception;

	int addSumToOrder(Map<String, Object> map) throws Exception;

	int updateOrderState(Map<String, Object> map)throws Exception;

	Float getUserMoney(int user_id);

	int subUserMoney(Map<String, Object> map)throws Exception;

	int updateOrderUserInfo(Map<String, Object> map);

	int updateCheckOrderInfo(Map<String, Object> map);

	int addCheckToOrder(Map<String, Object> map);

	List<Order> getWaitingOrderListByDescTime();

	List<Order> getTakedOrderListByDistId(int dist_id);

	List<Order> getFinishedOrderListByDistId(int dist_id);

	List<MedInOrder> getMedListByOrderId(int order_id);

	OrderDetail getOrderInfoByOrderId(int order_id);

	int takeOrderByDistId(Map<String, Object> map)throws Exception; //订单状态更新为被接单

	int finishOrder(int order_id)throws Exception;//订单状态更新为完成配送

	List<Order> getStartingOrderByUserId(int user_id);

	List<Order> getFinishedOrderByUserId(int user_id);
}
