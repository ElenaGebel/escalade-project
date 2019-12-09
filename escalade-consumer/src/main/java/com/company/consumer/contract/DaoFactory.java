package com.company.consumer.contract;

import com.company.consumer.contract.dao.UserDao;
import com.company.consumer.contract.dao.TopoDao;
import com.company.consumer.contract.dao.SpotDao;

public interface DaoFactory {

	UserDao getUserDao();
	
	void setUserDao(UserDao userDao);
	
	SpotDao getSpotDao();
	
	void setSpotDao(SpotDao spotDao);
	
	TopoDao getTopoDao();

	void setTopoDao(TopoDao topoDao);

}
