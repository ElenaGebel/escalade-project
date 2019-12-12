
package com.company.webapp.controller;

import com.company.business.contract.managers.SectorManager;
import com.company.model.bean.Sector;
import com.company.model.bean.User;
import com.company.webapp.AbstractController;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;


@Controller
public class SectorController  extends AbstractController{
	
    private SectorManager sectorManager = getManagerFactory().getSectorManager();

    @GetMapping("/spot/{spotId}/sector")
    public ModelAndView listSectorsFromParent(ModelMap modelMap, @PathVariable String spotId, HttpServletRequest request) {
        Sector sector = new Sector();
        sector.setSpotId(Integer.parseInt(spotId));

        modelMap.addAttribute("currentURI", request.getRequestURI());
        modelMap.addAttribute("publicationId", spotId);
        modelMap.addAttribute("sectorList", sectorManager.getListSectors(sector));

        return new ModelAndView("sector", "sector", new Sector());
    }

    @PostMapping("/sector/{spotId}/sector")
    public String addSector(@ModelAttribute Sector sector, @SessionAttribute User user, @PathVariable String spotId) {
        //sector.setUserId(user.getId());
        sector.setSpotId(Integer.parseInt(spotId));

        sectorManager.registerSector(sector);
        return "redirect:/spot/" + spotId;
    }

    @PostMapping("/spot/{spotId}/sector/{sectorId}/update")
    public String updateSector(@ModelAttribute Sector sector, @PathVariable String spotId, @PathVariable String sectorId) {
        sector.setId(Integer.parseInt(sectorId));

        sectorManager.updateSector(sector);
        return "redirect:/spot/" + spotId;
    }

    @PostMapping("/spot/{spotId}/sector/{sectorId}/delete")
    public String deleteSector(@ModelAttribute Sector sector, @PathVariable String spotId, @PathVariable String sectorId) {
        sector.setId(Integer.parseInt(sectorId));

        sectorManager.deleteSector(sector);
        return "redirect:/spot/" + spotId;
    }
}
