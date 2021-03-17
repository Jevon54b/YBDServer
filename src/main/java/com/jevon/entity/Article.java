package com.jevon.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Article {

	private String id;
	private String title;
	private String author;
	private Timestamp realease_time;
	private String content;
	private String pic;
	private int read_num;
	private int comment_num;
	
	
	
	
	public int getRead_num() {
		return read_num;
	}
	public void setRead_num(int read_num) {
		this.read_num = read_num;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Timestamp getRealease_time() {
		return realease_time;
	}
	public void setRealease_time(Timestamp realease_time) {
		this.realease_time = realease_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
