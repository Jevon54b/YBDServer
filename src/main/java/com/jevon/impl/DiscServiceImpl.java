package com.jevon.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jevon.dao.DiscDaoMapper;
import com.jevon.entity.Article;
import com.jevon.entity.Comment;
import com.jevon.service.DiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DiscServiceImpl implements DiscService {


	@Autowired
	DiscDaoMapper distMapper;

	@Override
	public boolean addArticle(String title, String author, String pic, String content) {
		Map<String,Object> map = new HashMap<>();
		map.put("title", title);
		map.put("author", author);
		map.put("content", content);
		map.put("pic", pic);
		distMapper.addArticle(map);
		return true;
	}

	@Override
	public Article getArticle(String id){
		distMapper.AddArticleReadNum(id);
		return distMapper.selectArticleById(id);
	}
	
	@Override
	public List<Comment> getArticleCommentList(String id){
		return distMapper.getAllCommentByID(id);
	}
		
	@Override
	public List<Article> getArticleList(){
		return distMapper.getAllArticles();
	}

	@Override
	public boolean addaComment(String article_id, String commenter,
			String content, String user_id) {
		Map<String,Object> map = new HashMap<>();
		map.put("article_id", article_id);
		map.put("commenter", commenter);
		map.put("content", content);
		map.put("user_id", user_id);
		
		int result=distMapper.addComment(map);
		if (result==1) {
			distMapper.AddArticleCommentNum(article_id);
			return true;
		}else {
			return false;}
	}

	@Override
	public boolean deleteAComment(String id) {
		distMapper.deleteComment(id);
		
		return true;
	}

	@Override
	public boolean deleteAnArticle(String id) {
		distMapper.deleteArticle(id);
		return true;
	}

	@Override
	public boolean updateArticleById(String author, String title, String content,String pic, String article_id) {
		Map<String,Object> map = new HashMap<>();
		map.put("article_id", article_id);
		map.put("author", author);
		map.put("content", content);
		map.put("title", title);
		map.put("pic",pic);
		distMapper.updateArticleById(map);
		return true;
	}


}
