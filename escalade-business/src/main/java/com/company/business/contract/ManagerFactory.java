package com.company.business.contract;

import com.company.business.contract.managers.SpotManager;
import com.company.business.contract.managers.TopoManager;
import com.company.business.contract.managers.UserManager;


public interface ManagerFactory {
	
	UserManager getUserManager();
	
	void setUserManager(UserManager userManager);
	
	SpotManager getSpotManager();

	void setSpotManager(SpotManager spotManager);
	
	TopoManager getTopoManager();

	void setTopoManager(TopoManager topoManager);


}
