/*
* Controller class for index.html (login page)
* Spring Controller maps URLs with RequestMapping annotations
* html target is returned as a String
* this controller simply redirects from root "/" (localhost:8080) to view index.html
* TODO set up encryption for user name and password fields
* */


package com.sx.controllers;

import com.sx.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if(sessionUser.getAuthorities()!=null&&sessionUser.getAuthorities().toString().contains("TRAINER")) {
            return "redirect:/login2";
        }else if(sessionUser.getAuthorities()!=null&&sessionUser.getAuthorities().toString().contains("ADMIN")){
            return "redirect:/login";
        }
        return "/index";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        SecurityContextHolder.clearContext();
        return "redirect:/?logout";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
}
