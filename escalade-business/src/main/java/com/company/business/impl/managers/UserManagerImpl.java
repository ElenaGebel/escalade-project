package com.company.business.impl.managers;

import com.company.business.impl.AbstractManager;
import com.company.model.bean.User;

import java.util.List;

import com.company.business.contract.managers.UserManager;

public class UserManagerImpl extends AbstractManager implements UserManager{
	
	public void registerUser(User user) {
		getDaoFactory().getUserDao().registerUser(user);
	}
	
	public void deleteUser(User user) {
		getDaoFactory().getUserDao().deleteUser(user);
	}
	
	public void updateUser(User user) {
		getDaoFactory().getUserDao().updateUser(user);
	}
	
	public void logIn(User user) {
		getDaoFactory().getUserDao().logIn(user);
	}
	
	public List<User> getListUsers(){
		return getDaoFactory().getUserDao().getListUsers();
	}
	

}
