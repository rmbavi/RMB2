package com.example.rmb.entity;

import java.io.Serializable;

/**
 * 
 新闻标题 id： 标识 title: 标题内容
 * 
 * @author Administrator
 * 
 */
public class NewsPaperTitle implements Serializable {

	private int id;
	private String title;

	public NewsPaperTitle(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public NewsPaperTitle() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
