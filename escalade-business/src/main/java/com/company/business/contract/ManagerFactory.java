package com.company.business.contract;

import com.company.business.contract.managers.UserManager;


public interface ManagerFactory {
	
	UserManager getUserManager();
	
	void setUserManager(UserManager userManager);


}
