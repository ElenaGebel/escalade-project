package com.company.model.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	private int statusReservation;
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

		if (reservationDate != null) {
			SimpleDateFormat formattedDate = new SimpleDateFormat("dd MMMM yyyy");    
			Calendar c = Calendar.getInstance(); 
			c.setTime(reservationDate);
			c.add(Calendar.DATE, 14); 
            return (String)(formattedDate.format(c.getTime()));
		} else
            return "";
	}
	
	public String statusReservationMessage(Boolean param) {
		if (statusReservation == 1)
            return "Topo réservé. Date de fin de réservation " + getReservationEndDate() + ".";
        else if (statusReservation == 2)
            return "Demande de réservation en cours.";
        else if (statusReservation == 2 && param)
            return "Votre réservation est en cours de traitement.";
        else if(param)
        	return "Réservation est refusé.";
        else 
        	return "Topo n'est pas réservé.";
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
	
	public int getStatusReservation() {
		return statusReservation;
	}
	public void setStatusReservation(int statusReservation) {
		this.statusReservation = statusReservation;
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
