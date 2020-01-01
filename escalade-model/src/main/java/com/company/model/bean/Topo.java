package com.company.model.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Topo {
	private int id;
	private int userId; 
	private String name;
	private String image;
	private String description;
	private Date date;
	private Date reservationDate;
	private Date reservationEndDate;
	private Boolean reserved;
	private int userReservedId; 
	
	public String getReservationDate() {
		if (reservationDate != null)
            return new SimpleDateFormat("dd MMMM yyyy").format(reservationDate);
        else
            return null;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getReservationEndDate() {
		if (reservationEndDate != null)
            return new SimpleDateFormat("dd MMMM yyyy").format(reservationEndDate);
        else
            return null;
	}
	public void setReservationEndDate(Date reservationEndDate) {
		this.reservationEndDate = reservationEndDate;
	}
	public Boolean getReserved() {
		return reserved;
	}
	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}
	public int getUserReservedId() {
		return userReservedId;
	}
	public void setUserReservedId(int userReservedId) {
		this.userReservedId = userReservedId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
