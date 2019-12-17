package com.company.model.bean;


public class Route {
	private int id;
	private int sectorId; 
	private int parentId;

	private String name;
	private String description;
	private int height;
	private int quotation;
	private int pointsNum;
	private String type;

	private String laititude;
	private String longitude;
	private int userId; 
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLaititude() {
		return laititude;
	}
	public void setLaititude(String laititude) {
		this.laititude = laititude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public int getPointsNum() {
		return pointsNum;
	}
	public void setPointsNum(int pointsNum) {
		this.pointsNum = pointsNum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSectorId() {
		return sectorId;
	}
	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getQuotation() {
		return quotation;
	}
	public void setQuotation(int quotation) {
		this.quotation = quotation;
	}	

}
