package com.jevon.controller;

import javax.servlet.http.HttpServletRequest;

import com.jevon.entity.ServerResponse;
import com.jevon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mysql.cj.xdevapi.Result;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@Controller
@RequestMapping(value = "/order/")
public class OrderController {

	@Autowired
	OrderService os;

	@ResponseBody
	@RequestMapping(value = "addMedToCurOrder.do", method = RequestMethod.POST)
	public ServerResponse addMed(HttpServletRequest rs) {
		int user_id = Integer.parseInt(rs.getParameter("user_id"));
		int med_id = Integer.parseInt(rs.getParameter("med_id"));
		int result = os.addMedToCurOrder(med_id, user_id);
		if (result == 1) {
			return ServerResponse.createBySuccessMessage("成功加入购物车");
		} else {
			return ServerResponse.createByErrorMessage("添加购物车失败");
		}
	}

	@ResponseBody
	@RequestMapping(value = "getMedListFromCurOrder.do", method = RequestMethod.GET)
	public ServerResponse getCurMedList(HttpServletRequest rs) {
		int user_id = Integer.parseInt(rs.getParameter("user_id"));
		return ServerResponse.createBySuccess("获取购物车药品成功", os.getMedListFromCurOrder(user_id));
	}

	@ResponseBody
	@RequestMapping(value = "subMednum.do", method = RequestMethod.POST)
	public ServerResponse subMednum(HttpServletRequest rs) {
		int om_id = Integer.parseInt(rs.getParameter("om_id"));
		int user_id = Integer.parseInt(rs.getParameter("user_id"));
		int result = os.subMednumInOrder(om_id, user_id);
		if (result == 1) {
			return ServerResponse.createBySuccessMessage("成功更新药品数量");
		} else if (result == 0) {
			return ServerResponse.createByErrorMessage("更新药品数量失败");
		} else {
			return ServerResponse.createBySuccessMessage("成功更新药品，但更新总额失败");
		}
	}

	@ResponseBody
	@RequestMapping(value = "postNormalOrder.do", method = RequestMethod.POST)
	public ServerResponse postNormalOrder(HttpServletRequest rs) {
		int user_id = Integer.parseInt(rs.getParameter("user_id"));
		int speed = Integer.parseInt(rs.getParameter("speed"));
		String phone = rs.getParameter("user_phone");
		String name = rs.getParameter("user_name");
		String address = rs.getParameter("user_address");
		int result = os.payNormalOrder(user_id, speed,name,phone,address);
		switch (result) {
			case 1:
				return ServerResponse.createBySuccessMessage("订单支付成功");
			case -1:
				return ServerResponse.createByErrorMessage("钱包余额不足!");
			case -2:
				return ServerResponse.createByErrorMessage("用户余额扣除失败，订单支付失败!");
			case 0:
				return ServerResponse.createByErrorMessage("余额扣除成功，订单提交失败");
			case 3:
				return ServerResponse.createBySuccessMessage("订单支付成功但数据库更新总额失败");
			default:
				return ServerResponse.createByErrorMessage("未知错误");
		}

	}


	@ResponseBody
	@RequestMapping(value="postPrescriptOrder.do", method=RequestMethod.POST)
	public ServerResponse postPrescriptOrder(MultipartHttpServletRequest mrs) {
		int user_id = Integer.parseInt(mrs.getParameter("user_id"));
		String user_name = mrs.getParameter("user_name");
		String user_phone = mrs.getParameter("user_phone");
		String user_address = mrs.getParameter("user_address");
		int speed = Integer.parseInt(mrs.getParameter("speed"));
		MultipartFile file = mrs.getFile("pic");
		String filename = file.getOriginalFilename();
		System.out.println(filename);
		InputStream inputStream;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return ServerResponse.createByErrorMessage("图片上传失败");
		} //获取文件输入流
		OutputStream osm=null;
		try {
			String path = "D:\\FTPStoreRoot\\prescipt_order_verify_pic\\";
			byte[] bs = new byte[1024];
			int len;
			File tmpFile = new File(path);
			if (!tmpFile.exists()) {
				tmpFile.mkdirs();
			}
			osm = new FileOutputStream(tmpFile.getPath()+File.separator+filename);
			while((len = inputStream.read(bs))!= -1) {
				osm.write(bs,0,len);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				osm.close();
				inputStream.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		//图片存放服务器地址
		String picPath = "http://245z3l3522.qicp.vip/file/prescipt_order_verify_pic/"+filename;
		int result = os.postPrescriptOrder(user_id, speed, user_name, user_phone, user_address, picPath);
		switch (result) {
			case 1:
				return ServerResponse.createBySuccessMessage("订单已提交审核");
			case -1:
				return ServerResponse.createByErrorMessage("钱包余额不足!");
			case -2:
				return ServerResponse.createByErrorMessage("用户余额扣除失败，订单支付失败!");
			case 0:
				return ServerResponse.createByErrorMessage("余额扣除成功，订单提交失败");
			case 3:
				return ServerResponse.createBySuccessMessage("订单支付成功但数据库更新总额失败");
			default:
				return ServerResponse.createByErrorMessage("未知错误");
		}
	}

	@ResponseBody
	@RequestMapping(value="getWaitingOrderList.do",method=RequestMethod.GET)
	public ServerResponse getWaitingOrder() {
		return ServerResponse.createBySuccess("获取订单列表成功", os.getWaitingOrderByTimeDesc());
	}

	@ResponseBody
	@RequestMapping(value="getOrderDetail.do",method=RequestMethod.GET)
	public ServerResponse getOrderDetail(HttpServletRequest rs) {
		int order_id = Integer.parseInt(rs.getParameter("order_id"));
		return ServerResponse.createBySuccess("获取订单详请成功", os.getOrderDetailById(order_id));
	}

	@ResponseBody
	@RequestMapping(value="takeOrder.do",method=RequestMethod.POST)
	public ServerResponse takeOrder(HttpServletRequest rs) {
		int dist_id = Integer.parseInt(rs.getParameter("dist_id"));
		int order_id = Integer.parseInt(rs.getParameter("order_id"));
		int result = os.takeOrderByDistId(dist_id, order_id);
		if (result == 0) {
			return ServerResponse.createByErrorMessage("接取订单失败");
		}else {
			return ServerResponse.createBySuccessMessage("成功接取订单");
		}
	}

	@ResponseBody
	@RequestMapping(value="finishOrder.do",method=RequestMethod.GET)
	public ServerResponse finishOrder(HttpServletRequest rs) {
		int order_id = Integer.parseInt(rs.getParameter("order_id"));
		int result = os.finishOrder(order_id);
		if (result == 0) {
			return ServerResponse.createByErrorMessage("完成订单失败");
		}else {
			return ServerResponse.createBySuccessMessage("成功完成订单");
		}
	}

	@ResponseBody
	@RequestMapping(value="getTakedOrderList.do",method=RequestMethod.GET)
	public ServerResponse getTakedOrder(HttpServletRequest rs) {
		int dist_id = Integer.parseInt(rs.getParameter("dist_id"));
		return ServerResponse.createBySuccess("获取已接单列表成功", os.getTakedOrder(dist_id));
	}

	@ResponseBody
	@RequestMapping(value="getFinishedOrderList.do",method=RequestMethod.GET)
	public ServerResponse getFinishedOrder(HttpServletRequest rs) {
		int dist_id = Integer.parseInt(rs.getParameter("dist_id"));
		return ServerResponse.createBySuccess("获取已完成列表成功", os.getFinishedOrder(dist_id));
	}


	@ResponseBody
	@RequestMapping(value="getFinishedOrderByUserId.do",method=RequestMethod.GET)
	public ServerResponse getFinishedOrderByUserId(HttpServletRequest rs) {
		int user_id = Integer.parseInt(rs.getParameter("user_id"));
		return ServerResponse.createBySuccess("获取已完成订单列表成功", os.getFinishedOrderByUserId(user_id));
	}

	@ResponseBody
	@RequestMapping(value="getStartingOrderByUserId.do",method=RequestMethod.GET)
	public ServerResponse getStartingOrderByUserId(HttpServletRequest rs) {
		int user_id = Integer.parseInt(rs.getParameter("user_id"));
		return ServerResponse.createBySuccess("获取当前订单列表成功", os.getStartingOrderByUserId(user_id));
	}


}
