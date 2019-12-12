package com.company.consumer.contract;

import com.company.consumer.contract.dao.UserDao;
import com.company.consumer.contract.dao.TopoDao;
import com.company.consumer.contract.dao.SpotDao;
import com.company.consumer.contract.dao.CommentDao;
import com.company.consumer.contract.dao.SectorDao;
import com.company.consumer.contract.dao.RouteDao;

public interface DaoFactory {

	UserDao getUserDao();
	
	void setUserDao(UserDao userDao);
	
	SpotDao getSpotDao();
	
	void setSpotDao(SpotDao spotDao);
	
	TopoDao getTopoDao();

	void setTopoDao(TopoDao topoDao);
	
	CommentDao getCommentDao();

	void setCommentDao(CommentDao commentDao);

	SectorDao getSectorDao();

	void setSectorDao(SectorDao sectorDao);

	RouteDao getRouteDao();

	void setRouteDao(RouteDao routeDao);

}
