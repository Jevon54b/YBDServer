package com.jevon.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Comment {

	private String id;
	private String article_id;
	private String commenter;
	private String content;
	private Timestamp realease_time;
	private String commenter_id;
	
	
	
	public String getCommenter_id() {
		return commenter_id;
	}
	public void setCommenter_id(String commenter_id) {
		this.commenter_id = commenter_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getCommenter() {
		return commenter;
	}
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRealease_time() {
		return realease_time;
	}

	public void setRealease_time(Timestamp realease_time) {
		this.realease_time = realease_time;
	}
}
