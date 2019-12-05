package com.company.webapp.controller;


import com.company.business.contract.managers.UserManager;
import com.company.model.bean.Reponse;
import com.company.model.bean.User;
import com.company.webapp.AbstractController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserController extends AbstractController{

	private UserManager userManager = getManagerFactory().getUserManager();
       

    @GetMapping("/inscription")
    public String inscription(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }

    	return "inscription";
    }
    
    @PostMapping("/inscription")
    public String addUser(HttpServletRequest request) {
        User user = new User();
        user.setPseudo(request.getParameter("pseudo"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password")); 
        
        Reponse reponse = userManager.registerUser(user);
        if (reponse.getIsError()) {

        	HttpSession session = request.getSession();
            session.setAttribute("reponse", reponse);
        	return "inscription";
        } else {
        	
            User loggedUser = userManager.logIn(user);

            HttpSession session = request.getSession();
            session.setAttribute("user", loggedUser);
            session.setAttribute("reponse", null);
            return "redirect:/";           
        }
    }
    
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        return "/login";
    }

    @PostMapping("/login")
    public String userLogin(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        User loggedUser = userManager.logIn(user);

        HttpSession session = request.getSession();

        if (request.getParameter("remember") != null && loggedUser != null) {
            session.setAttribute("user", loggedUser);
            return "redirect:/";
        } else {
            session.invalidate();
        }

        return "redirect:/login";
    }
    
    @GetMapping("/logout")
    public String userLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
    
    @GetMapping("/account")
    public String userAccount(ModelMap modelMap, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        return "account";
    }

    @PostMapping("/account")
    public String updateUser(@PathVariable("userId") String userId, ModelMap modelMap, RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setPseudo(request.getParameter("pseudo"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("new_password"));
        
        Reponse reponse;
        if (request.getParameter("last_password").equals(user.getPassword())) {
        	reponse =  userManager.updateUser(user);
        } else {
        	reponse = new Reponse(true, "Mauvais mot de passe !");
            
        }
        redirectAttributes.addAttribute("reponse", reponse);
        return "redirect:/account";
    }

    @PostMapping("/account/delete")
    public String deleteUser(@PathVariable String userId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        userManager.deleteUser(user);

        session.invalidate();
        return "redirect:/";
    }
}
