package com.jevon.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.jevon.entity.ServerResponse;
import com.jevon.service.UserService;
import com.jevon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value="/user/")
@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping(value="register.do",method=RequestMethod.POST)
	public ServerResponse registerDistributer(HttpServletRequest rs) {
		String phone = rs.getParameter("phone");
		String nick = rs.getParameter("nick");
		String password = rs.getParameter("password");
		if (StringUtils.isNullOrEmpty(phone)) {
			return ServerResponse.createByErrorMessage("注册失败，手机为空");
		}
		if (StringUtils.isNullOrEmpty(password)) {
			return ServerResponse.createByErrorMessage("注册失败，密码为空");
		}
		if (StringUtils.isNullOrEmpty(nick)) {
			return ServerResponse.createByErrorMessage("注册失败，昵称为空");
		}
		
		int result = userService.addUser(phone, password, nick);
		
		if (result == 1) {
			return ServerResponse.createBySuccessMessage("注册成功");
		}else {
			return ServerResponse.createByErrorMessage("注册失败");
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	public ServerResponse loginUser(HttpServletRequest rs) {
		String phone = rs.getParameter("phone");
		String password = rs.getParameter("password");
		if (StringUtils.isNullOrEmpty(phone)) {
			return ServerResponse.createByErrorMessage("登录失败，手机为空");
		}
		if (StringUtils.isNullOrEmpty(password)) {
			return ServerResponse.createByErrorMessage("登录失败，密码为空");
		}
		int result = userService.isVaild(phone, password);
		switch (result) {
		case 0:
			return ServerResponse.createByErrorMessage("登录名或密码输入错误");
		case 1:
			return ServerResponse.createBySuccess("登录成功", userService.getUserByPhone(phone));
		default:
			return ServerResponse.createByErrorMessage("系统错误");
		}
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value="getAllUser.do",method=RequestMethod.GET)
	public ServerResponse getAllUser(){
		return ServerResponse.createBySuccess("获取用户列表成功",userService.getAllUser());
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "getUserCount.do",method = RequestMethod.GET)
	public ServerResponse getUserCount(){
		return ServerResponse.createBySuccess("获取用户数量成功","{'userCount':"+userService.getAllUser().size()+"}");
	}

	@ResponseBody
	@RequestMapping(value="createAddressInfo.do",method=RequestMethod.POST)
	public ServerResponse createAddressInfo(HttpServletRequest rs){

		String address = rs.getParameter("address");
		String phone = rs.getParameter("phone");
		String user_name = rs.getParameter("user_name");
		String userIdStr = rs.getParameter("user_id");
		String latitudeStr = rs.getParameter("latitude");
		String longitudeStr = rs.getParameter("longitude");
		int user_id = Integer.valueOf(userIdStr);
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		userService.createAddressInfo(user_id,address,phone,user_name,latitude,longitude);
		return ServerResponse.createBySuccessMessage("新增收货地址成功");
	}

	@ResponseBody
	@RequestMapping(value="updateAddressInfo.do",method=RequestMethod.POST)
	public ServerResponse updateAddressInfo(HttpServletRequest rs){
		int id = Integer.valueOf(rs.getParameter("id"));
		String address = rs.getParameter("address");
		String phone = rs.getParameter("phone");
		String user_name = rs.getParameter("user_name");
		double latitude = Double.valueOf(rs.getParameter("latitude"));
		double longitude = Double.valueOf(rs.getParameter("longitude"));
		userService.updateAddressInfo(id,address,phone,user_name,latitude,longitude);
		return ServerResponse.createBySuccessMessage("修改收货地址成功");
	}

	@ResponseBody
	@RequestMapping(value="getAddressInfoList.do",method=RequestMethod.GET)
	public ServerResponse getAddressInfoList(HttpServletRequest rs){
		int user_id = Integer.valueOf( rs.getParameter("user_id"));
		return ServerResponse.createBySuccess("获取收货地址列表成功",userService.getAddressInfoList(user_id));
	}

	@ResponseBody
	@RequestMapping(value="getLastUseAddressInfo.do",method=RequestMethod.GET)
	public ServerResponse getLastUseAddressInfo(HttpServletRequest rs){
		int user_id = Integer.valueOf( rs.getParameter("user_id"));
		return ServerResponse.createBySuccess("获取最近收货地址成功",userService.getLastUseAddressInfo(user_id));
	}
}
