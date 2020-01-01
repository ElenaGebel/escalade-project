package com.company.model.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
	private int id;
	private int userId; 
	private int parentId;
	private int topoId;
	private String text;
	private Date date;
	
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getTopoId() {
		return topoId;
	}
	public void setTopoId(int topoId) {
		this.topoId = topoId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
    public String getFormatDate() {
        if (date != null)
            return new SimpleDateFormat("dd MMMM yyyy, hh:mm").format(date);
        else
            return null;
    }
	public void setDate(Date date) {
		this.date = date;
	}

}
