package com.jevon.service;

import com.jevon.entity.Article;
import com.jevon.entity.Comment;

import java.util.List;


public interface DiscService {
	
	public Article getArticle(String id);
	
	public List<Comment> getArticleCommentList(String id);
	
	public List<Article> getArticleList();
	
	public boolean addaComment(String article_id, String commenter,
			String content, String user_id);
	
	public boolean deleteAComment(String id);
	
	public boolean deleteAnArticle(String id);
	
}
