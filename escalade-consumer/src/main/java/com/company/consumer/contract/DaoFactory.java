package com.company.consumer.contract;

import com.company.consumer.contract.dao.UserDao;

public interface DaoFactory {

	UserDao getUserDao();
	
	void setUserDao(UserDao userDao);

}
