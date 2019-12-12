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
    public String addTopo(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException  {
    	Topo topo = new Topo();
   	
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");

        topo.setUserId(user.getId());
        topo.setName(request.getParameter("name"));
        topo.setDescription(request.getParameter("description"));
          
        topo.setDate(new Date());
        
        topo.setId(topoManager.registerTopo(topo));

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();

            // Creating the directory to store file
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/image/topo");
            
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            File serverFile = new File(dir.getAbsolutePath() + File.separator + "topo-" + topo.getId() + ".jpg");
           
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


            topo.setImage("image/topo/" + serverFile.getName());
        } else {
            topo.setImage("");
        }

        topoManager.updateTopo(topo);
        
        List<Topo> topos = topoManager.getListTopos();

        session.setAttribute("topos", topos);
        
        return "topo";
    }
    

    @PostMapping("/topo/{topoId}/update")
    public String updateTopo(@ModelAttribute Topo topo, @PathVariable String topoId, @RequestParam String description, @RequestParam MultipartFile file, @RequestParam String currentPicture, HttpServletRequest request) throws IOException {
        topo.setId(Integer.parseInt(topoId));
        topo.setDescription(description);

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();

            // Creating the directory to store file
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/image/topo");
            
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            File serverFile = new File(dir.getAbsolutePath() + File.separator + "topo-" + topo.getId() + ".jpg");
           
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


            topo.setImage("image/topo/" + serverFile.getName());
        } else {
            topo.setImage("");
        }

        topoManager.updateTopo(topo);
        

        return "redirect:/topo";
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
   /*   modelMap.addAttribute("topo", topoManager.getTopo(topo));
        modelMap.addAttribute("notRelatedSpots", topoManager.getNotRelatedSpots(topo));
        modelMap.addAttribute("topoHasSpots", topoManager.getTopoHasSpot(topo));
        modelMap.addAttribute("notRelatedUser", topoManager.getNotRelatedUser(topo));
        modelMap.addAttribute("userHasTopos", topoManager.getUserHasTopo(topo));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));*/
        return "topo_item";
    }

    @PostMapping("/topo-spot/{topoId}")
    public String addTopoHasSpot(@PathVariable String topoId, @RequestParam int spotId) {

        Spot spot = new Spot();
        spot.setId(spotId);
        spot.setTopoId(Integer.parseInt(topoId));

        spotManager.addTopoToSpot(spot);
        return "redirect:/topo/" + topoId;
    }

    @PostMapping("/topo-spot/{spotId}/delete")
    public String deleteTopoHasSpot(@PathVariable String spotId, @RequestParam int topoId) {
        Spot spot = new Spot();
        spot.setId(Integer.parseInt(spotId));
        spot.setTopoId(0);

        spotManager.addTopoToSpot(spot);
        
        return "redirect:/topo/" + topoId;
    }




}

