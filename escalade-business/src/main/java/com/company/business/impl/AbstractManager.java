package com.company.business.impl;

import com.company.consumer.contract.DaoFactory;

public abstract class AbstractManager {
	
	private static DaoFactory daoFactory;

	public static DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public static void setDaoFactory(DaoFactory daoFactory) {
		AbstractManager.daoFactory = daoFactory;
	}

}
