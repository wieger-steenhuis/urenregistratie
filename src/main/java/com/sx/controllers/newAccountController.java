package com.sx.controllers;

import com.sx.models.Trainer;
import com.sx.models.User_roles;
import com.sx.service.TrainerService;
import com.sx.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * Created by udr013 on 8-7-2016.
 */
@Controller
public class newAccountController {

    @Autowired
    TrainerService trainerService;

    @Autowired
    UserRoleService userRoleService;

    @RequestMapping("/register")
    public String registerPage(Model model){
        Collection<Trainer> allUsers = (Collection<Trainer>) trainerService.findAll();
        for(Trainer trainer:allUsers){
            System.out.println(trainer);
        }
        model.addAttribute("trainer", new Trainer());
        model.addAttribute("user_roles", new User_roles());
        model.addAttribute("allUsers", allUsers);
        return "register";
    }

    @RequestMapping(value = {"/register/save"}, method = RequestMethod.POST)
    public String registerPage(@ModelAttribute("trainer") Trainer trainer,@ModelAttribute("user_roles") User_roles user_roles, Model model) {
        if(trainer.getPassword().equals(trainer.getConfirmPassword())&&trainer.getPassword()!=null) {
            try {
                trainerService.save(trainer);
                user_roles.setUsername(trainer.getUsername());
                userRoleService.save(user_roles);

            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                model.addAttribute("registerError", true);
                trainer.setUsername(null);
                return "register";
            }
        }else {
            model.addAttribute("passwordError",true);
            trainer.setPassword(null);
            trainer.setConfirmPassword(null);
            return "register";
        }

        System.out.println(trainer);
        System.out.println("saving user");
        model.addAttribute("registered", true);
        return "index";
}}
