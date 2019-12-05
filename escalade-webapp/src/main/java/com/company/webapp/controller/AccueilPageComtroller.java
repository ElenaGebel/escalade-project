package com.company.webapp.controller;

import com.company.business.contract.managers.SpotManager;
import com.company.model.bean.Spot;
import com.company.webapp.AbstractController;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import org.springframework.ui.ModelMap;


@Controller
public class AccueilPageComtroller  extends AbstractController{
	
	private SpotManager spotManager = getManagerFactory().getSpotManager();
	
    @GetMapping("/")
    public String getAccueilInfo(ModelMap modelMap) {

        modelMap.addAttribute("spots", spotManager.getListSpots());
    	return "accueil";
    }
}


