package com.company.business.contract.managers;
import java.util.List;

import com.company.model.bean.Reponse;
import com.company.model.bean.User;

public interface UserManager {
	
	Reponse registerUser(User user);
	Reponse deleteUser(User user);
	User updateUser(User user);
	User logIn(User user);
	List<User> getListUsers();

}
