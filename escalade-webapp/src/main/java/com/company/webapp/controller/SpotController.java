package com.company.webapp.controller;


import com.company.business.contract.managers.SpotManager;
import com.company.model.bean.Spot;
import com.company.webapp.AbstractController;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class SpotController extends AbstractController{

	private SpotManager spotManager = getManagerFactory().getSpotManager();
       

    @GetMapping("/sites")
    public String showSites(HttpServletRequest request) {
        List<Spot> spots = spotManager.getListSpots();

        HttpSession session = request.getSession();
        session.setAttribute("spots", spots);

    	return "sites";
    }

    @PostMapping("/sites")
    public String addUser(HttpServletRequest request) {

        return "sites";
    }


}

