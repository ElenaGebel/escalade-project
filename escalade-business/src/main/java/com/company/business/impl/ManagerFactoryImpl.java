package com.company.business.impl;

import com.company.business.contract.ManagerFactory;
import com.company.business.contract.managers.UserManager;

public class ManagerFactoryImpl implements ManagerFactory {
	
	private UserManager userManager;

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}
