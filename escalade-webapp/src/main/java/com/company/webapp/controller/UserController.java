package com.company.webapp.controller;


import com.company.business.contract.managers.UserManager;
import com.company.model.bean.User;
import com.company.webapp.AbstractController;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController extends AbstractController{

	private UserManager userManager = getManagerFactory().getUserManager();
       

    @GetMapping("/")
    public String inscription(HttpServletRequest request) {
    	return "inscription";
    }

    @PostMapping("/inscription")
    public String addUser(HttpServletRequest request) {
	    User user = new User();
        user.setNickname(request.getParameter("nickname"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        	        
        userManager.registerUser(user);

        //User userQuery = userManager.logIn(user);

        //HttpSession session = request.getSession();
        //session.setAttribute("user", userQuery);
        return "inscription";
    }


}
