package com.company.business.impl;

import com.company.business.contract.ManagerFactory;
import com.company.business.contract.managers.SpotManager;
import com.company.business.contract.managers.UserManager;

public class ManagerFactoryImpl implements ManagerFactory {
	
	private UserManager userManager;
	private SpotManager spotManager;

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
