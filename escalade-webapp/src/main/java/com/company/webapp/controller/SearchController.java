package com.company.webapp.controller;


import com.company.webapp.AbstractController;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class SearchController extends AbstractController{

    @GetMapping("/search")
    public String searchForm() {
        return "search";
    }

}

