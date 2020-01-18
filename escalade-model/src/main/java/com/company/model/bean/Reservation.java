package com.company.model.bean;

import java.util.Date;

public class Reservation {
	private int userId;
	private int topoId;
	private Boolean reserved;
	private int statusReservation;

	private Date date;
	
	private Date finDate;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTopoId() {
		return topoId;
	}
	public void setTopoId(int topoId) {
		this.topoId = topoId;
	}
	public Boolean getReserved() {
		return reserved;
	}
	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}
	public int getStatusReservation() {
		return statusReservation;
	}
	public void setStatusReservation(int statusReservation) {
		this.statusReservation = statusReservation;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getFinDate() {
		return finDate;
	}
	public void setFinDate(Date finDate) {
		this.finDate = finDate;
	}
}
