package com.jevon.controller;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;

import com.jevon.entity.Article;
import com.jevon.entity.Comment;
import com.jevon.entity.ServerResponse;
import com.jevon.service.DiscService;
import com.jevon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value="/disc/")
@Controller
public class DiscController {

	@Autowired
	DiscService discService;
	
	@ResponseBody
	@RequestMapping(value="getArticleList.do",method=RequestMethod.GET)
	public ServerResponse getArticleList(HttpServletRequest rs) {

		List<Article> articlelist=discService.getArticleList();
		
		return ServerResponse.createBySuccess("SUCCESS", articlelist);
	}
	
	@ResponseBody
	@RequestMapping(value="getArticleDetail.do",method=RequestMethod.POST)
	public ServerResponse getArticleDetail(HttpServletRequest rs) {
		String id = rs.getParameter("id");
		Article article=discService.getArticle(id);
//		System.out.println(article);
//		return ServerResponse.createBySuccess("SUCCESS", article);
		if(article!=null)
			return ServerResponse.createBySuccess("SUCCESS", article);
		return ServerResponse.createByErrorMessage("ERROR");
	}
	
	@ResponseBody
	@RequestMapping(value="getArticleComment.do",method=RequestMethod.POST)
	public ServerResponse getArticleComment(HttpServletRequest rs) {
		//responseData:commentList
		String id = rs.getParameter("id");
		List<Comment> commentlist=discService.getArticleCommentList(id);
		if(commentlist!=null){
			return ServerResponse.createBySuccess("SUCCESS", commentlist);
		}
		return ServerResponse.createByErrorMessage("ERROR");
	}

	@ResponseBody
	@RequestMapping(value="addComment.do",method=RequestMethod.POST)
	public ServerResponse addComment(@RequestParam Map<String,String> params) {

		String article_id = params.get("article_id");
		String commenter = params.get("user_id");
		String content = params.get("content");
		String username = params.get("username");
		
		if(discService.addaComment( article_id, username, content,commenter))
		return ServerResponse.createBySuccess("SUCCESS");
		else {
			return ServerResponse.createByError();
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="deleteComment.do",method=RequestMethod.POST)
	public ServerResponse deleteComment(HttpServletRequest rs) {

		String id = rs.getParameter("id");
		boolean result=discService.deleteAComment(id);
		if (result)
			return ServerResponse.createBySuccess("SUCCESS");
		else
			return ServerResponse.createByError();
	}
	
	
	
	
	
}