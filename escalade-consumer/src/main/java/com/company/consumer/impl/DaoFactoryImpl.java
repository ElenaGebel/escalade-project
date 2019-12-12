package com.company.consumer.impl;

import com.company.consumer.contract.DaoFactory;
import com.company.consumer.contract.dao.SpotDao;
import com.company.consumer.contract.dao.UserDao;
import com.company.consumer.contract.dao.TopoDao;
import com.company.consumer.contract.dao.CommentDao;
import com.company.consumer.contract.dao.SectorDao;
import com.company.consumer.contract.dao.RouteDao;

public class DaoFactoryImpl implements DaoFactory {
	
	private UserDao userDao;
	private SpotDao spotDao;
	private TopoDao topoDao;
	
	private CommentDao commentDao;
	private SectorDao sectorDao;
	private RouteDao routeDao;

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public SectorDao getSectorDao() {
		return sectorDao;
	}

	public void setSectorDao(SectorDao sectorDao) {
		this.sectorDao = sectorDao;
	}

	public RouteDao getRouteDao() {
		return routeDao;
	}

	public void setRouteDao(RouteDao routeDao) {
		this.routeDao = routeDao;
	}

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
