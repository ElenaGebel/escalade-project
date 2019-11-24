package com.company.consumer.contract.dao;

import com.company.model.bean.User;

public interface UserDao {
	
	void registerUser(User user);
	void deleteUser(User user);
	void updateUser(User user);
	void logIn(User user);

}
