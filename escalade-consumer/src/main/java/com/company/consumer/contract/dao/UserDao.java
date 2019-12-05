package com.company.consumer.contract.dao;

import java.util.List;

import com.company.model.bean.Reponse;
import com.company.model.bean.User;

public interface UserDao {
	
	Reponse registerUser(User user);
	Reponse deleteUser(User user);
	Reponse updateUser(User user);
	User logIn(User user);
	List<User> getListUsers();
	Boolean checkExistingUserEmail(User user);

}
