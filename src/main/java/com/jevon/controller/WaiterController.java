package com.jevon.controller;

import com.jevon.entity.ServerResponse;
import com.jevon.service.UserService;
import com.jevon.service.WaiterService;
import com.jevon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value="/waiter/")
@Controller
public class WaiterController {
    @Autowired
    WaiterService waiterService;

    @ResponseBody
    @RequestMapping(value="register.do",method= RequestMethod.POST)
    public ServerResponse registerWaiter(HttpServletRequest rs) {
        String account = rs.getParameter("account");
        String name = rs.getParameter("name");
        String password = rs.getParameter("password");
        if (StringUtils.isNullOrEmpty(account)) {
            return ServerResponse.createByErrorMessage("注册失败，账号为空");
        }
        if (StringUtils.isNullOrEmpty(password)) {
            return ServerResponse.createByErrorMessage("注册失败，密码为空");
        }
        if (StringUtils.isNullOrEmpty(name)) {
            return ServerResponse.createByErrorMessage("注册失败，名字为空");
        }

        int result = waiterService.addWaiter(account, password, name);

        if (result == 1) {
            return ServerResponse.createBySuccessMessage("注册成功");
        }else {
            return ServerResponse.createByErrorMessage("注册失败");
        }

    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value="login.do",method=RequestMethod.POST)
    public ServerResponse loginWaiter(HttpServletRequest rs) {
        String account = rs.getParameter("account");
        String password = rs.getParameter("password");
        String value = rs.getParameter("value");
        System.out.println(value);
        if (StringUtils.isNullOrEmpty(account)) {
            return ServerResponse.createByErrorMessage("登录失败，手机为空");
        }
        if (StringUtils.isNullOrEmpty(password)) {
            return ServerResponse.createByErrorMessage("登录失败，密码为空");
        }
        int result = waiterService.isVaild(account, password);
        switch (result) {
            case 0:
                return ServerResponse.createByErrorMessage("登录名或密码输入错误");
            case 1:
                return ServerResponse.createBySuccess("登录成功", waiterService.getWaiterByAccount(account));
            default:
                return ServerResponse.createByErrorMessage("系统错误");
        }
    }
}
