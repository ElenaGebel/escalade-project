package com.company.webapp.controller;

import com.company.business.contract.managers.RouteManager;
import com.company.business.contract.managers.SectorManager;
import com.company.model.bean.Sector;
import com.company.model.bean.User;
import com.company.model.bean.Route;
import com.company.webapp.AbstractController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class RouteController  extends AbstractController{
	
	   private RouteManager routeManager =  getManagerFactory().getRouteManager();
	   private SectorManager sectorManager = getManagerFactory().getSectorManager();
	   
	    @GetMapping("/route")
	    public ModelAndView listRoutesFromParent(ModelMap modelMap, HttpServletRequest request) {

	        modelMap.addAttribute("sectors", sectorManager.getListSectors(null));
	        modelMap.addAttribute("currentURI", request.getRequestURI());
	        modelMap.addAttribute("routeList", routeManager.listRoutesFromParent(null));
	        modelMap.addAttribute("quotations", routeManager.listQuotation());

	        return new ModelAndView("route", "route", new Route());
	    }

	    @PostMapping("/route")
	    public String addRoute(@ModelAttribute Route route, HttpServletRequest request) {
	    	HttpSession session = request.getSession();
	    	User user = (User) session.getAttribute("user");

	        route.setUserId(user.getId());
	        route.setSectorId(Integer.parseInt(request.getParameter("sectorId")));
	        route.setType("route");

	        routeManager.registerRoute(route);
	        return "redirect:/route";
	    }

	    @PostMapping("/route/{routeId}/update")
	    public String updateRoute(@ModelAttribute Route route, @PathVariable String routeId, HttpServletRequest request) {
	        route.setId(Integer.parseInt(routeId));
	        route.setSectorId(Integer.parseInt(request.getParameter("sectorId")));
	        routeManager.updateRoute(route);
	        return "redirect:/route";
	    }

	    @PostMapping("/route/{routeId}/delete")
	    public String deleteRoute(@ModelAttribute Route route, @PathVariable String routeId) {
	        route.setId(Integer.parseInt(routeId));

	        routeManager.deleteRoute(route);
	        return "redirect:/route";
	    }

	    @GetMapping("/route/{routeId}")
	    public ModelAndView getRoute(ModelMap modelMap, @PathVariable String routeId, HttpServletRequest request) {
	        Route route = new Route();
	        route.setId(Integer.parseInt(routeId));
	        route.setParentId(Integer.parseInt(routeId));

	        modelMap.addAttribute("currentURI", request.getRequestURI());
	        modelMap.addAttribute("routeId", routeId);
	        modelMap.addAttribute("route", routeManager.getRoute(route));
	        modelMap.addAttribute("listLength", routeManager.listLengthsFromRoute(route));
	        modelMap.addAttribute("quotations", routeManager.listQuotation());
	        return new ModelAndView("route_item", "length", new Route());
	    }

	    @PostMapping("/route/{parentId}/{sectorId}")
	    public String addLength(@ModelAttribute Route length, @SessionAttribute User user, @PathVariable String parentId, @PathVariable String sectorId) {
	        length.setUserId(user.getId());
	        length.setSectorId(Integer.parseInt(sectorId));
	        length.setParentId(Integer.parseInt(parentId));
	        length.setType("length");

	        routeManager.registerRoute(length);
	        return "redirect:/route/" + parentId;
	    }


}
