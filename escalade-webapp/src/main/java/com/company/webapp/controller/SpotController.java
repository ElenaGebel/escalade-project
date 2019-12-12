package com.company.webapp.controller;


import com.company.business.contract.managers.SpotManager;
import com.company.model.bean.Comment;
import com.company.model.bean.Spot;
import com.company.model.bean.Topo;
import com.company.model.bean.User;
import com.company.webapp.AbstractController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class SpotController extends AbstractController{

	private SpotManager spotManager = getManagerFactory().getSpotManager();
       

    @GetMapping("/spot")
    public ModelAndView listSpots(ModelMap modelMap) {
        modelMap.addAttribute("spots", spotManager.getListSpots());
        
        return new ModelAndView("spot", "spot", new Spot());
    }
    
    @PostMapping("/spot")
    public String addSpot(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException  {
    	Spot spot = new Spot();
   	
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");

    	spot.setName(request.getParameter("name"));
    	spot.setDescription(request.getParameter("description"));
          
    	spot.setDate(new Date());
        
    	spot.setId(spotManager.registerSpot(spot));

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();

            // Creating the directory to store file
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/image/spot");
            
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            File serverFile = new File(dir.getAbsolutePath() + File.separator + "spot-" + spot.getId() + ".jpg");
           
            BufferedOutputStream stream;
			try {
				stream = new BufferedOutputStream(
				        new FileOutputStream(serverFile));
	            stream.write(bytes);
	            stream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


            spot.setImage("image/spot/" + serverFile.getName());
        } else {
        	spot.setImage("");
        }
        
        spotManager.updateSpot(spot);
        
        List<Spot> spots = spotManager.getListSpots();

        session.setAttribute("spots", spots);
        
        return "redirect:/spot";
    }

    @GetMapping("/spot/{spotId}")
    public String getTopo(ModelMap modelMap, @PathVariable String spotId, HttpServletRequest request) {
        Spot spot = new Spot();

        spot.setId(Integer.parseInt(spotId));

        modelMap.addAttribute("currentURI", request.getRequestURI());

        modelMap.addAttribute("spot", spotManager.getSpot(spot));
        modelMap.addAttribute("topo", spotManager.checkForTopo(spot));

        return "spot_item";
    }
    

    @PostMapping("/spot/{spotId}/update")
    public String updateSpot(@ModelAttribute Spot spot, @PathVariable String spotId, @RequestParam String description, @RequestParam MultipartFile file, @RequestParam String currentPicture, HttpServletRequest request) throws IOException {
        spot.setId(Integer.parseInt(spotId));
        spot.setDescription(description);

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();

            // Creating the directory to store file
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/image/spot");
            
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            File serverFile = new File(dir.getAbsolutePath() + File.separator + "spot-" + spot.getId() + ".jpg");
           
            BufferedOutputStream stream;
			try {
				stream = new BufferedOutputStream(
				        new FileOutputStream(serverFile));
	            stream.write(bytes);
	            stream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			spot.setImage("image/spot/" + serverFile.getName());
        } else {
        	spot.setImage("");
        }

        spotManager.updateSpot(spot);
        

        return "redirect:/spot";
    }



    
    @PostMapping("/spot/{spotId}/picture-delete")
    public String deletespotPicture(@ModelAttribute Spot spot, @PathVariable String spotId, @RequestParam String picture, HttpServletRequest request) {
    	spot.setId(Integer.parseInt(spotId));
      
        
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/image/spot");
        File dir = new File(path);
        
        if (dir.exists()) {
            File file = new File(dir.getAbsolutePath() + File.separator + "spot-" + spot.getId() + ".jpg");          
            file.delete();
        	
        }
        spot.setImage("");
        spotManager.updateSpot(spot);

        return "redirect:/spot/" + spotId;
    }

    @PostMapping("/spot/{spotId}/delete")
    public String deletespot(@ModelAttribute Spot spot, @PathVariable String spotId, @RequestParam String picture, HttpServletRequest request) {
        spot.setId(Integer.parseInt(spotId));
        spot.setImage(picture);

        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/image/spot");
        File dir = new File(path);
        
        if (dir.exists()) {
            File file = new File(dir.getAbsolutePath() + File.separator + "spot-" + spot.getId() + ".jpg");          
            file.delete();
        	
        }

        spotManager.deleteSpot(spot);
        return "redirect:/spot";
    }


}

