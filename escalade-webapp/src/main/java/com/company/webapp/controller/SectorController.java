
package com.company.webapp.controller;

import com.company.business.contract.managers.SectorManager;
import com.company.business.contract.managers.SpotManager;
import com.company.model.bean.Sector;
import com.company.model.bean.User;
import com.company.webapp.AbstractController;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;


@Controller
public class SectorController  extends AbstractController{
	
    private SectorManager sectorManager = getManagerFactory().getSectorManager();
    private SpotManager spotManager = getManagerFactory().getSpotManager();

    @GetMapping("/sector")
    public ModelAndView listSectors(ModelMap modelMap, HttpServletRequest request) {

        modelMap.addAttribute("currentURI", request.getRequestURI());
        modelMap.addAttribute("sectorList", sectorManager.getListSectors(null));
        modelMap.addAttribute("spots", spotManager.getListSpots());

        return new ModelAndView("sector", "sector", new Sector());
    }

    @PostMapping("/sector")
    public String addSector(@ModelAttribute Sector sector, @RequestParam int spotId,  HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
        sector.setUserId(user.getId());
        sector.setSpotId(spotId);

        sectorManager.registerSector(sector);
        return "redirect:/sector";
    }

    @PostMapping("/sector/{sectorId}/update")
    public String updateSector(@ModelAttribute Sector sector,  @PathVariable String sectorId,  HttpServletRequest request) {
        sector.setId(Integer.parseInt(sectorId));
        sector.setSpotId(Integer.parseInt(request.getParameter("spotId")));
        sector.setDescription(request.getParameter("description")); 
        sectorManager.updateSector(sector);
        return "redirect:/sector";
    }

    @PostMapping("/sector/{sectorId}/delete")
    public String deleteSector(@ModelAttribute Sector sector, @PathVariable String sectorId) {
        sector.setId(Integer.parseInt(sectorId));

        sectorManager.deleteSector(sector);
        return "redirect:/sector";
    }
}
