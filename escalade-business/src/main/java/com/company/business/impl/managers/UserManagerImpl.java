package com.company.business.impl.managers;

import com.company.business.impl.AbstractManager;
import com.company.model.bean.Reponse;
import com.company.model.bean.User;

import java.util.List;

import com.company.business.contract.managers.UserManager;

public class UserManagerImpl extends AbstractManager implements UserManager{
	
	public Reponse registerUser(User user) {
		if(!getDaoFactory().getUserDao().checkExistingUserEmail(user))
		    return getDaoFactory().getUserDao().registerUser(user);
		
		return new Reponse(true, "Email exist");
	}
	
	public Reponse deleteUser(User user) {
		return getDaoFactory().getUserDao().deleteUser(user);
	}
	
	public User updateUser(User user) {
		return getDaoFactory().getUserDao().updateUser(user);
	}
	
	public User logIn(User user) {
		return getDaoFactory().getUserDao().logIn(user);
	}
	
	public List<User> getListUsers(){
		return getDaoFactory().getUserDao().getListUsers();
	}
	

}
