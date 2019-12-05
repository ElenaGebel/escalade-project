package com.company.consumer.impl;

import com.company.consumer.contract.DaoFactory;
import com.company.consumer.contract.dao.SpotDao;
import com.company.consumer.contract.dao.UserDao;

public class DaoFactoryImpl implements DaoFactory {
	
	private UserDao userDao;
	private SpotDao spotDao;

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
