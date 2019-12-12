package com.company.business.impl;

import com.company.business.contract.ManagerFactory;
import com.company.business.contract.managers.SpotManager;
import com.company.business.contract.managers.TopoManager;
import com.company.business.contract.managers.UserManager;
import com.company.business.contract.managers.CommentManager;
import com.company.business.contract.managers.SectorManager;
import com.company.business.contract.managers.RouteManager;

public class ManagerFactoryImpl implements ManagerFactory {
	
	private UserManager userManager;
	private SpotManager spotManager;
	private TopoManager topoManager;
	
	private CommentManager commentManager;
	private RouteManager routeManager;
	private SectorManager sectorManager;
	
	
	public CommentManager getCommentManager() {
		return commentManager;
	}

	public void setCommentManager(CommentManager commentManager) {
		this.commentManager = commentManager;
	}

	public RouteManager getRouteManager() {
		return routeManager;
	}

	public void setRouteManager(RouteManager routeManager) {
		this.routeManager = routeManager;
	}

	public SectorManager getSectorManager() {
		return sectorManager;
	}

	public void setSectorManager(SectorManager sectorManager) {
		this.sectorManager = sectorManager;
	}



	public TopoManager getTopoManager() {
		return topoManager;
	}

	public void setTopoManager(TopoManager topoManager) {
		this.topoManager = topoManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public SpotManager getSpotManager() {
		return spotManager;
	}

	public void setSpotManager(SpotManager spotManager) {
		this.spotManager = spotManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}
