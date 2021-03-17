package com.jevon.controller;

import javax.servlet.http.HttpServletRequest;

import com.jevon.entity.ServerResponse;
import com.jevon.service.MedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping(value="/medicine/")
public class MedController {
	
	@Autowired
	MedService ms;
	
	@ResponseBody
	@RequestMapping(value="getTop5MedList.do",method=RequestMethod.GET)
	public ServerResponse getTop3MedList(HttpServletRequest rs) {
		return ServerResponse.createBySuccess("获取热销列表成功", ms.getTopSalesnumMedList());
	}
	
	@ResponseBody
	@RequestMapping(value="getMedicineListByTypeId.do",method=RequestMethod.GET)
	public ServerResponse getMedicineListByType(HttpServletRequest rs) {
		int t_id = Integer.parseInt(rs.getParameter("type_id"));
		int sort_way = Integer.parseInt(rs.getParameter("sort_flag"));
		return ServerResponse.createBySuccess("获取类型列表成功", ms.getMedListByType(t_id,sort_way));
	}
	
	@ResponseBody
	@RequestMapping(value="getMedicineDetail.do",method=RequestMethod.GET)
	public ServerResponse getMedicineDetail(HttpServletRequest rs) {
		int m_id = Integer.parseInt(rs.getParameter("med_id"));
		return ServerResponse.createBySuccess("获取药品详情成功",ms.getMedDetailById(m_id) );
	}
	
	@ResponseBody
	@RequestMapping(value="searchMed.do",method=RequestMethod.GET)
	public ServerResponse searchMed(HttpServletRequest rs) {
		String keyword = rs.getParameter("keyword");
		int sort_way = Integer.parseInt(rs.getParameter("sort_flag"));
		return ServerResponse.createBySuccess("搜索药品成功", ms.searchMedByKeyword(keyword,sort_way));
	}

}
