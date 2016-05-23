/*
* Controller class for index.html (login page)
* Spring Controller maps URLs with RequestMapping annotations
* html target is returned as a String
* this controller simply redirects from root "/" (localhost:8080) to view index.html
* TODO add admin (and trainer) account verification through user name and password
* TODO set up encryption for user name and password fields
* */


package com.sx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    //controller classes map server requests and return html templates (String value)
    //when the adress '/' (localhost:8080) is requested this controller returns index.html
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
