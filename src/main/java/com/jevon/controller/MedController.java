package com.jevon.controller;

import javax.servlet.http.HttpServletRequest;

import com.jevon.entity.ServerResponse;
import com.jevon.service.MedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/medicine/")
public class MedController {

    @Autowired
    MedService ms;

    @ResponseBody
    @RequestMapping(value = "getTop5MedList.do", method = RequestMethod.GET)
    public ServerResponse getTop3MedList(HttpServletRequest rs) {
        return ServerResponse.createBySuccess("获取热销列表成功", ms.getTopSalesnumMedList());
    }

    @ResponseBody
    @RequestMapping(value = "getMedicineListByTypeId.do", method = RequestMethod.GET)
    public ServerResponse getMedicineListByType(HttpServletRequest rs) {
        int t_id = Integer.parseInt(rs.getParameter("type_id"));
        int sort_way = Integer.parseInt(rs.getParameter("sort_flag"));
        return ServerResponse.createBySuccess("获取类型列表成功", ms.getMedListByType(t_id, sort_way));
    }

    @ResponseBody
    @RequestMapping(value = "getMedicineDetail.do", method = RequestMethod.GET)
    public ServerResponse getMedicineDetail(HttpServletRequest rs) {
        int m_id = Integer.parseInt(rs.getParameter("med_id"));
        return ServerResponse.createBySuccess("获取药品详情成功", ms.getMedDetailById(m_id));
    }

    @ResponseBody
    @RequestMapping(value = "searchMed.do", method = RequestMethod.GET)
    public ServerResponse searchMed(HttpServletRequest rs) {
        String keyword = rs.getParameter("keyword");
        int sort_way = Integer.parseInt(rs.getParameter("sort_flag"));
        return ServerResponse.createBySuccess("搜索药品成功", ms.searchMedByKeyword(keyword, sort_way));
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "getAllMedicine.do", method = RequestMethod.GET)
    public ServerResponse getAllMedicine(HttpServletRequest rs) {
        String limit = rs.getParameter("limit");
        String offset = rs.getParameter("offset");
        return ServerResponse.createBySuccess("获取药品列表成功", ms.getAllMedicine(limit, offset));
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "getMedicineCount.do", method = RequestMethod.GET)
    public ServerResponse getMedicineCount() {
        return ServerResponse.createBySuccess("获取药品列表成功", ms.getMedicineCount());
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "updateMedicine.do", method = RequestMethod.POST)
    public ServerResponse updateMedicine(HttpServletRequest rs) {
        String id = rs.getParameter("id");
        String name = rs.getParameter("name");
        float price = Float.valueOf(rs.getParameter("price"));
        int prescript = Integer.valueOf(rs.getParameter("prescript"));
        String note = rs.getParameter("note");
        String pic = rs.getParameter("pic");
        int med_type = Integer.valueOf(rs.getParameter("med_type"));
        String normal_name = rs.getParameter("normal_name");
        String goods_name = rs.getParameter("goods_name");
        String composition = rs.getParameter("composition");
        String avoid = rs.getParameter("avoid");
        String function = rs.getParameter("function");
        String usage = rs.getParameter("usage");
        String properties = rs.getParameter("properties");
        String packing_size = rs.getParameter("packing_size");
        String adverse_reaction = rs.getParameter("adverse_reaction");
        String store_condition = rs.getParameter("store_condition");
        String valid_time = rs.getParameter("valid_time");
        String attensions = rs.getParameter("attensions");
        String register_number = rs.getParameter("register_number");
        String manufacturer = rs.getParameter("manufacturer");
        ms.updateMedicine(id,name,price,prescript,note,pic,med_type,normal_name,goods_name,composition,avoid,function,usage,properties,packing_size,adverse_reaction,store_condition,valid_time,attensions,register_number,manufacturer);
        return ServerResponse.createBySuccessMessage("更新药品成功");
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "addMedicine.do", method = RequestMethod.POST)
    public ServerResponse addMedicine(HttpServletRequest rs) {
        String name = rs.getParameter("name");
        float price = Float.valueOf(rs.getParameter("price"));
        int prescript = Integer.valueOf(rs.getParameter("prescript"));
        String note = rs.getParameter("note");
        String pic = rs.getParameter("pic");
        int med_type = Integer.valueOf(rs.getParameter("med_type"));
        String normal_name = rs.getParameter("normal_name");
        String goods_name = rs.getParameter("goods_name");
        String composition = rs.getParameter("composition");
        String avoid = rs.getParameter("avoid");
        String function = rs.getParameter("function");
        String usage = rs.getParameter("usage");
        String properties = rs.getParameter("properties");
        String packing_size = rs.getParameter("packing_size");
        String adverse_reaction = rs.getParameter("adverse_reaction");
        String store_condition = rs.getParameter("store_condition");
        String valid_time = rs.getParameter("valid_time");
        String attensions = rs.getParameter("attensions");
        String register_number = rs.getParameter("register_number");
        String manufacturer = rs.getParameter("manufacturer");
        ms.addMedicine(name,price,prescript,note,pic,med_type,normal_name,goods_name,composition,avoid,function,usage,properties,packing_size,adverse_reaction,store_condition,valid_time,attensions,register_number,manufacturer);
        return ServerResponse.createBySuccessMessage("新增药品成功");
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "deleteMedicine.do", method = RequestMethod.GET)
    public ServerResponse deleteMedicine(HttpServletRequest rs) {
        String id = rs.getParameter("id");
        ms.deleteMedicine(id);
        return ServerResponse.createBySuccessMessage("删除药品成功");
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "getMedTypeList.do", method = RequestMethod.GET)
    public ServerResponse getMedTypeList(HttpServletRequest rs) {
        return ServerResponse.createBySuccess("获取药品类型列表成功",ms.getAllMedType());
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "addMedType.do", method = RequestMethod.GET)
    public ServerResponse addMedType(HttpServletRequest rs) {
        String name = rs.getParameter("name");
        ms.addMedType(name);
        return ServerResponse.createBySuccessMessage("创建药品类型成功");
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "getMedTypeByTypeId.do", method = RequestMethod.GET)
    public ServerResponse getMedTypeByTypeId(HttpServletRequest rs){
        String typeId = rs.getParameter("typeId");
        return ServerResponse.createBySuccess("获取药品类型成功",ms.getMedTypeByTypeId(typeId));
    }
}
