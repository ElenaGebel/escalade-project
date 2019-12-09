package com.company.webapp.controller;


import com.company.business.contract.managers.TopoManager;
import com.company.model.bean.Topo;
import com.company.model.bean.User;
import com.company.webapp.AbstractController;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class TopoController extends AbstractController{

	private TopoManager topoManager = getManagerFactory().getTopoManager();
       

    @GetMapping("/topos")
    public String showTopos(HttpServletRequest request) {
        List<Topo> topos = topoManager.getListTopos();
  
        HttpSession session = request.getSession();
        session.setAttribute("topos", topos);

    	return "topos";
    }

    @PostMapping("/topos/add")
    public String addTopo(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException  {
    	Topo topo = new Topo();
    	
    	HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("user="+user);
        topo.setUserId(user.getId());
        topo.setName(request.getParameter("name"));
        topo.setDescription(request.getParameter("description"));
        
        topo.setId(topoManager.registerTopo(topo));

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();

            // Creating the directory to store file

            String rootPath = "/Users/Elena/escalade/image";
            File dir = new File(rootPath + File.separator + "topo");
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


            topo.setImage("/image/topo/" + serverFile.getName());
        } else {
            topo.setImage("");
        }

        topoManager.updateTopo(topo);

        return "topos";
    }
    



}

