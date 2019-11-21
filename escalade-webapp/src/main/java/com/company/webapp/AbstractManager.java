package com.company.webapp;

import com.company.business.contract.ManagerFactory;

public abstract class AbstractManager {
	private static ManagerFactory managerFactory;
	
	public static ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public static void setManagerFactory(ManagerFactory managerFactory) {
		AbstractManager.managerFactory = managerFactory;
	}



}