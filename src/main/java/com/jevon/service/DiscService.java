package com.jevon.service;

import com.jevon.entity.Article;
import com.jevon.entity.Comment;

import java.util.List;


public interface DiscService {

	public boolean addArticle(String title,String author,String pic,String content);

	public Article getArticle(String id);
	
	public List<Comment> getArticleCommentList(String id);
	
	public List<Article> getArticleList();
	
	public boolean addaComment(String article_id, String commenter,
			String content, String user_id);
	
	public boolean deleteAComment(String id);
	
	public boolean deleteAnArticle(String id);

	public boolean updateArticleById(String author,String title,String content,String pic,String article_id);
	
}
