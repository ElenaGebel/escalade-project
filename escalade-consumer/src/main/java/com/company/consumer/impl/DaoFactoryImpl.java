package com.company.consumer.impl;

import com.company.consumer.contract.DaoFactory;
import com.company.consumer.contract.dao.SpotDao;
import com.company.consumer.contract.dao.UserDao;
import com.company.consumer.contract.dao.TopoDao;

public class DaoFactoryImpl implements DaoFactory {
	
	private UserDao userDao;
	private SpotDao spotDao;
	private TopoDao topoDao;

	public TopoDao getTopoDao() {
		return topoDao;
	}

	public void setTopoDao(TopoDao topoDao) {
		this.topoDao = topoDao;
	}

	public SpotDao getSpotDao() {
		return spotDao;
	}

	public void setSpotDao(SpotDao spotDao) {
		this.spotDao = spotDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	

}
