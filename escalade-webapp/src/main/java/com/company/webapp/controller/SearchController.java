package com.company.webapp.controller;


import com.company.business.contract.managers.RouteManager;
import com.company.business.contract.managers.SectorManager;
import com.company.business.contract.managers.SpotManager;
import com.company.business.contract.managers.TopoManager;
import com.company.model.bean.Route;
import com.company.model.bean.Sector;
import com.company.model.bean.Spot;
import com.company.model.bean.Topo;
import com.company.webapp.AbstractController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class SearchController extends AbstractController{
	
	   private RouteManager routeManager =  getManagerFactory().getRouteManager();
	   private SectorManager sectorManager = getManagerFactory().getSectorManager();
	   private TopoManager topoManager =  getManagerFactory().getTopoManager();
	   private SpotManager spotManager = getManagerFactory().getSpotManager();
	   
    @GetMapping("/search")
    public String searchForm(ModelMap modelMap, HttpServletRequest request) {
    	modelMap.addAttribute("quotations", routeManager.listQuotation());
        return "search";
    }
    
    @PostMapping("/search")
    public String listPublication(ModelMap modelMap, HttpServletRequest request) {
        String typePublication = request.getParameter("type_publication");
        String textPublication = request.getParameter("text_name_description");
        Integer quotation = Integer.parseInt(request.getParameter("quotation"));

        int height;
        if (request.getParameter("height").equals("")) height = 100;
        else height = Integer.parseInt(request.getParameter("height"));

        int points;
        if (request.getParameter("points_number").equals("")) points = 0;
        else points = Integer.parseInt(request.getParameter("points_number"));

        if (typePublication.equals("spot")) {
            Spot spot = new Spot();
            spot.setName(textPublication);
            modelMap.addAttribute("publicationList", spotManager.listForSearch(spot));
        } else if (typePublication.equals("sector")) {
            Sector sector = new Sector();
            sector.setName(textPublication);
            modelMap.addAttribute("publicationList", sectorManager.listForSearch(sector));
        } else if (typePublication.equals("route")) {
            Route route = new Route();
            route.setName(textPublication);
            route.setHeight(height);
            route.setQuotation(quotation);
            route.setPointsNum(points);
            modelMap.addAttribute("publicationList", routeManager.listForSearch(route));
        } else {
            Topo topo = new Topo();
            topo.setName(textPublication);
            modelMap.addAttribute("publicationList", topoManager.listForSearch(topo));
        }
        modelMap.addAttribute("quotations", routeManager.listQuotation());
        modelMap.addAttribute("typePublication", typePublication);

        return "search";
    }

}

