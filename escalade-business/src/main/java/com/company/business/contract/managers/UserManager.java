package com.company.business.contract.managers;
import java.util.List;

import com.company.model.bean.User;

public interface UserManager {
	
	void registerUser(User user);
	void deleteUser(User user);
	void updateUser(User user);
	void logIn(User user);
	List<User> getListUsers();

}
