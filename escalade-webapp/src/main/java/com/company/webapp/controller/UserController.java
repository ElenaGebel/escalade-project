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
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Controller
public class UserController extends AbstractController{

	private UserManager userManager = getManagerFactory().getUserManager();
	private Pattern pattern;
	private Matcher matcher;

	private static final String PASSWORD_PATTERN = 
          "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,20})";
      

    @GetMapping("/inscription")
    public String inscription(HttpServletRequest request) {
    	HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        session.invalidate();
    	return "inscription";
    }
    
    @PostMapping("/inscription")
    public String addUser(HttpServletRequest request) {
    	
        User user = new User();
        user.setPseudo(request.getParameter("pseudo"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password")); 
        
        HttpSession session = request.getSession();
        
        if(user.getPseudo().length() < 1) {
        	session.setAttribute("reponse", new Reponse(true, "Pseudo ne peut pas etre vide"));
        	return "inscription";
        }
        
        if(!validate(user.getPassword())) {
        	session.setAttribute("reponse", new Reponse(true, "Votre mot de passe doit comporter de 6 à 20 caractères, "
        			+ "se composer de chiffres et de lettres et comprendre "
        			+ "des majuscules/minuscules ou des caractères spéciaux."));
        	return "inscription";
        }
        
        if(!EmailValidator.getInstance().isValid(user.getEmail())) {
        	session.setAttribute("reponse", new Reponse(true, "Adresse email n'est pas valide"));
        	return "inscription";
        }

        Reponse reponse = userManager.registerUser(user);
        if (reponse.getIsError()) {
            session.setAttribute("reponse", reponse);
        	return "inscription";
        } else {
        	
            User loggedUser = userManager.logIn(user);

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
        session.invalidate();
        return "/login";
    }

    @PostMapping("/login")
    public String userLogin(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        HttpSession session = request.getSession();
        
        if(user.getEmail().length() < 1) {
        	session.setAttribute("reponse", new Reponse(true, "Email ne peut pas etre vide"));
        	return "login";
        }
        
        if(user.getPassword().length() < 1) {
        	session.setAttribute("reponse", new Reponse(true, "Password ne peut pas etre vide"));
        	return "login";
        }

        User loggedUser = userManager.logIn(user);

        if (loggedUser != null) {
            session.setAttribute("user", loggedUser);
            return "redirect:/";
        } else {
        	session.setAttribute("reponse", new Reponse(true, "Login ou password incorrect."));
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
    public String updateUser(ModelMap modelMap, RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("user="+ user.getPassword());
        System.out.println("request.getParameter(\"last_password\")="+ request.getParameter("last_password"));
        System.out.println("request.getParameter(\"new_password\")="+ request.getParameter("new_password"));
        Reponse reponse;
        
        if(request.getParameter("pseudo").length() < 1) {
        	reponse = new Reponse(true, "Pseudo ne peut pas etre vide");
        }else if(!validate(request.getParameter("new_password"))) {
        	reponse = new Reponse(true, "Votre mot de passe doit comporter de 6 à 20 caractères, "
        			+ "se composer de chiffres et de lettres et comprendre "
        			+ "des majuscules/minuscules ou des caractères spéciaux.");
        }else if(!EmailValidator.getInstance().isValid(request.getParameter("email"))) {
        	reponse = new Reponse(true, "Adress email pas valide");
        	
        }else {
        	reponse =  userManager.updateUser(user);
    	    if(!reponse.getIsError()) {
                user.setPseudo(request.getParameter("pseudo"));
                user.setEmail(request.getParameter("email"));
                user.setPassword(request.getParameter("new_password"));
    	     }  
        	
        /*	if (BCrypt.checkpw(request.getParameter("last_password"), user.getPassword())) {
        	    reponse =  userManager.updateUser(user);
        	    if(!reponse.getIsError()) {
                    user.setPseudo(request.getParameter("pseudo"));
                    user.setEmail(request.getParameter("email"));
                    user.setPassword(request.getParameter("new_password"));
        	     }       		
        	}else 
        	    reponse = new Reponse(true, "Mauvais mot de passe !");   */   
        }
        session.setAttribute("reponse", reponse);

        return "redirect:/account";
    }

    @PostMapping("/account/delete")
    public String deleteUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        userManager.deleteUser(user);

        session.invalidate();
        return "redirect:/";
    }
    
    /**
	   * Validate password with regular expression
	   * @param password password for validation
	   * @return true valid password, false invalid password
	   */
	  public boolean validate(String password){
		  pattern = Pattern.compile(PASSWORD_PATTERN);
		  matcher = pattern.matcher(password);
		  return matcher.matches();
	    	    
	  }

}