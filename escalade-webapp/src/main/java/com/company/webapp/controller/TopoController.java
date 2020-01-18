package com.company.webapp.controller;


import com.company.business.contract.managers.CommentManager;
import com.company.business.contract.managers.SpotManager;
import com.company.business.contract.managers.TopoManager;
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
import org.springframework.web.bind.annotation.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class TopoController extends AbstractController{

	private TopoManager topoManager = getManagerFactory().getTopoManager();
	private SpotManager spotManager = getManagerFactory().getSpotManager();
	private CommentManager comments = getManagerFactory().getCommentManager();       
	

    @GetMapping("/topo")
    public ModelAndView listTopo(ModelMap modelMap) {
        modelMap.addAttribute("topos",  topoManager.getListTopos());
        return new ModelAndView("topo", "topo", new Topo());
    }

    @PostMapping("/topo")
    public String addTopo(@ModelAttribute Topo topo, @RequestParam MultipartFile file, HttpServletRequest request) throws IOException  {
   	
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");

        topo.setUserId(user.getId());
          
        topo.setDate(new Date());
        
        topo.setId(topoManager.registerTopo(topo));

        ServletContext context = request.getServletContext();

        String fileName =  "topo" + topo.getId() + "-" + UUID.randomUUID().toString() + ".jpg";
        
        if(uploadImage(file, context.getRealPath("/image/topo"), fileName))
        	topo.setImage("image/topo/" + fileName);
        else
        	topo.setImage(request.getParameter("currentPicture"));

        topoManager.updateTopo(topo);
        
        return "redirect:/topo";
    }
    

    @PostMapping("/topo/{topoId}/update")
    public String updateTopo(@PathVariable String topoId, @RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        
    	Topo topo = new Topo();
    	
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");

        topo.setUserId(user.getId());

    	topo.setName(request.getParameter("name"));
    	topo.setDescription(request.getParameter("description"));    	
    	topo.setId(Integer.parseInt(topoId));
        
        ServletContext context = request.getServletContext();

        String fileName =  "topo" + topo.getId() + "-" + UUID.randomUUID().toString() + ".jpg";
        
        if(uploadImage(file, context.getRealPath("/image/topo"), fileName))
        	topo.setImage("image/topo/" + fileName);
        else
        	topo.setImage(request.getParameter("currentPicture"));

        topoManager.updateTopo(topo);
        

        return "redirect:/topo";
    }
    
    public Boolean uploadImage(MultipartFile file, String path, String fileName) throws IOException{
    	
        if (!file.isEmpty()) {
 
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            file.transferTo(new File(dir.getAbsolutePath() + File.separator + fileName));

			return true;
        } else {
        	return false;
        }
    }

    @PostMapping("/topo/{topoId}/picture-delete")
    public String deleteTopoPicture(@ModelAttribute Topo topo, @PathVariable String topoId, @RequestParam String picture, HttpServletRequest request) {
        topo.setId(Integer.parseInt(topoId));
      
        
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/image/topo");
        File dir = new File(path);
        
        if (dir.exists()) {
            File file = new File(dir.getAbsolutePath() + File.separator + "topo-" + topo.getId() + ".jpg");          
            file.delete();
        	
        }
        topo.setImage("");
        topoManager.updateTopo(topo);

        return "redirect:/topo/" + topoId;
    }

    
    @PostMapping("/topo/{topoId}/delete")
    public String deleteTopo(@ModelAttribute Topo topo, @PathVariable String topoId, @RequestParam String picture, HttpServletRequest request) {
        topo.setId(Integer.parseInt(topoId));
        topo.setImage(picture);

        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/image/topo");
        File dir = new File(path);
        
        if (dir.exists()) {
            File file = new File(dir.getAbsolutePath() + File.separator + "topo-" + topo.getId() + ".jpg");          
            file.delete();
        	
        }

        topoManager.deleteTopo(topo);
        return "redirect:/topo";
    }

   @GetMapping("/topo/{topoId}")
    public String getTopo(ModelMap modelMap, @PathVariable String topoId, HttpServletRequest request) {
        Topo topo = new Topo();

        if (request.getSession().getAttribute("user") != null)
            topo.setUserId(((User) request.getSession().getAttribute("user")).getId());

        topo.setId(Integer.parseInt(topoId));

        Comment comment = new Comment();
        comment.setTopoId(Integer.parseInt(topoId));

        modelMap.addAttribute("currentURI", request.getRequestURI());

        modelMap.addAttribute("publicationId", topoId);
        modelMap.addAttribute("topo", topoManager.getTopo(topo));
                
        modelMap.addAttribute("notRelatedSpots", topoManager.getNotRelatedSpots(topo));
        modelMap.addAttribute("relatedSpots", topoManager.getRelatedSpots(topo));
        
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));
        return "topo_item";
    }

    @PostMapping("/topo/addspot/{topoId}")
    public String addTopoHasSpot(@PathVariable String topoId, @RequestParam int spotId) {

        Spot spot = new Spot();
        spot.setId(spotId);
        spot.setTopoId(Integer.parseInt(topoId));

        spotManager.addTopoToSpot(spot);
        return "redirect:/topo/" + topoId;
    }

    @PostMapping("/topo/deletespot/{spotId}")
    public String deleteTopoHasSpot(@PathVariable String spotId, @RequestParam int topoId) {
        Spot spot = new Spot();
        spot.setId(Integer.parseInt(spotId));
        spot.setTopoId(0);

        spotManager.addTopoToSpot(spot);
        
        return "redirect:/topo/" + topoId;
    }

    @PostMapping("/topo/reserver/{topoId}")
    public String askForReservation(@PathVariable String topoId, HttpServletRequest request) {
    	 Topo topo = new Topo();
         topo.setUserReservedId(((User) request.getSession().getAttribute("user")).getId());

         topo.setId(Integer.parseInt(topoId));
         topo.setReserved(false);
         topo.setStatusReservation(2);
        
         topoManager.updateReservation(topo);
         return "redirect:/topo/" + topoId;
    }

    @PostMapping("/reservation/cancel")
    public String cancelReservation(@RequestParam int topoId, @RequestParam int userReservedId, HttpServletRequest request) {  
    	Topo topo = new Topo();
    	topo.setId(topoId);
    	topo.setUserReservedId(userReservedId);
        topo.setReserved(false);
        topo.setStatusReservation(0);
        topoManager.updateReservation(topo);
        return "redirect:/reservation";
    }
    
    @PostMapping("/reservation/accepte")
    public String accepteReservation(@RequestParam int topoId, @RequestParam int userReservedId, HttpServletRequest request) {
    	Topo topo = new Topo();
    	topo.setId(topoId);
    	topo.setUserReservedId(userReservedId);

        topo.setReserved(true);
        topo.setStatusReservation(1);
        topo.setReservationDate(new Date());
        topoManager.updateReservation(topo);
        return "redirect:/reservation";
    }
    
    @GetMapping("/reservation")
    public String showReservationList(ModelMap modelMap, HttpServletRequest request) {

        if (request.getSession().getAttribute("user") != null) {
        	modelMap.addAttribute("topoList", topoManager.getListToposForUser((User) request.getSession().getAttribute("user")));
        	modelMap.addAttribute("reservationsList", topoManager.getListReservations((User) request.getSession().getAttribute("user")));
        }
            
        return "reservation";
    }


}

