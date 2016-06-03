/*
* Controller class for index.html (login page)
* Spring Controller maps URLs with RequestMapping annotations
* html target is returned as a String
* this controller simply redirects from root "/" (localhost:8080) to view index.html
* TODO add admin (and trainer) account verification through user name and password
* TODO set up encryption for user name and password fields
* */


package com.sx.controllers;

import com.sx.models.User_roles;
import com.sx.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@ComponentScan
public class IndexController {

    @Autowired
    TrainerService trainerService;

    //controller classes map server requests and return html templates (String value)
    //when the adress '/' (localhost:8080) is requested this controller returns index.html
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/loginTo")
    public String loginTo(Principal principal){
        User sessionUser =(org.springframework.security.core.userdetails.User)((Authentication)principal).getPrincipal();
       String username = sessionUser.getUsername();
        System.out.println(username);
        User_roles user_roles = new User_roles();
        System.out.println("getAuthorities"+sessionUser.getAuthorities());
        if(sessionUser.getAuthorities()!=null&&sessionUser.getAuthorities().toString().contains("TRAINER")) {
            System.out.println("in trainer"+sessionUser.getAuthorities() );
            return "redirect:/login2";
        }else if(sessionUser.getAuthorities()!=null&&sessionUser.getAuthorities().toString().contains("ADMIN")){
            System.out.println("in admin "+sessionUser.getAuthorities());
            return "redirect:/login";
        }
        System.out.println("not found Authority");
        return "/index";
    }


    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
}
