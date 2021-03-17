package com.jevon.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.jevon.entity.ServerResponse;
import com.jevon.service.UserService;
import com.jevon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
