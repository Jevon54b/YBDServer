package com.jevon.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.jevon.entity.ServerResponse;
import com.jevon.service.DistributerService;
import com.jevon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping(value="/distributer/")
@Controller
public class DistributerController {

	@Autowired
	DistributerService distService;
	
	@ResponseBody
	@RequestMapping(value="register.do",method=RequestMethod.POST)
	public ServerResponse registerDistributer(HttpServletRequest rs) {
		String phone = rs.getParameter("phone");
		String name = rs.getParameter("name");
		String password = rs.getParameter("password");
		String id_no = rs.getParameter("id_no");
		if (StringUtils.isNullOrEmpty(phone)) {
			return ServerResponse.createByErrorMessage("手机号为空");
		}
		if (StringUtils.isNullOrEmpty(password)) {
			return ServerResponse.createByErrorMessage("密码为空");
		}
		if (StringUtils.isNullOrEmpty(name)) {
			return ServerResponse.createByErrorMessage("姓名为空");
		}
		if (StringUtils.isNullOrEmpty(id_no)) {
			return ServerResponse.createByErrorMessage("身份证号码为空");
		}
		if(id_no.length()!=18) {
			return ServerResponse.createByErrorMessage("身份证号码非法");
		}
		
		int result = distService.addDistributer(phone, password, name, id_no);
		
		if (result == 1) {
			return ServerResponse.createBySuccessMessage("注册成功");
		}else {
			return ServerResponse.createByErrorMessage("注册失败");
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="login.do",method=RequestMethod.POST)
	public ServerResponse loginDistributer(HttpServletRequest rs) {
		String phone = rs.getParameter("phone");
		String password = rs.getParameter("password");
		if (StringUtils.isNullOrEmpty(phone)) {
			return ServerResponse.createByErrorMessage("手机号为空");
		}
		if (StringUtils.isNullOrEmpty(password)) {
			return ServerResponse.createByErrorMessage("密码为空");
		}
		int result = distService.isVaild(phone, password);
		switch (result) {
		case 0:
			return ServerResponse.createByErrorMessage("账户或密码错误");
		case 1:
			return ServerResponse.createByErrorMessage("等待审核中");
		case 2:
			return ServerResponse.createBySuccess("登录成功", distService.getDistByPhone(phone));
		case -1:
			return ServerResponse.createByErrorMessage("审核未通过");
		default:
			return ServerResponse.createByErrorMessage("登录失败,系统错误");
		}
		
		
		
	}
}
