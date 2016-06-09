/*
* Controller class for admin_home.html
* Spring Controller maps URLs with RequestMapping annotations
* html target is returned as a String
* uses Spring Model to add attributes to a view and to bind objects from a view to a java class using thymeleaf
**/

package com.sx.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

//controller class related to admin_home.html. This view multiple admin tasks and redirects to
@Controller
public class AdminHomeController {

    //controller uses enum values (inner class) in a List
    //.values() returns an array of all available enum values
    private List<Admin_Options>options = Arrays.asList(Admin_Options.values());

    //getter & setter:
    public List<Admin_Options> getOptions() {
        return options;
    }

    public void setOptions(List<Admin_Options> options) {
        this.options = options;
    }


    //log in form on index.html sends data with th:action="@{/login}" in thymeleaf
    //when this url is requested admin_home.html will be shown
    //admin_home is loaded with java object(s) through model.addAttributes("String identifier", object)
    //in this case the admin tasks(enum) are loaded in the view
    @RequestMapping("/login")
    public String adminhome(Model model){
        model.addAttribute("admin_opts", options);
        return "/admin_home";
    }

    //the form on admin_home uses th:action="@{/adminopts}" in thymeleaf
    //this form sends user input (chosen Admin_Task) to this 'adress' that can be read with method=RM.GET
    @RequestMapping(value="adminopts", method= RequestMethod.GET)
    //user input is identified in thymeleaf with name="optionsListId" and is bound (@ModelAttribute) to chosen-parameter
    //because the Admin_Options enum has a html target String, the chosen parameter takes care of redirecting to the right view
    public String choseAdminAction(@ModelAttribute("optionsListId") Admin_Options chosen) {
        return chosen.getHtmltarget();
    }

    //Enum Admin_Options contains different admin tasks to chose from
    //each constant contains a discription (to select in our views) and a specific html target to redirect to
    private enum Admin_Options {

        //admin tasks:
        CUSTOMER("Klant toevoegen of wijzigen", "/edit_customer"),
        TRAINER("Trainer toevoegen of wijzigen", "/edit_trainer"),
        RAPPORTAGE("(Maand)rapportages maken", "/admin_reporting");

        //enum variables:
        private String option;
        private String htmltarget;

        //contructor (implicitly private):
        Admin_Options(String option, String htmltarget) {
            this.option = option;
            this.htmltarget = htmltarget;
        }

        //getters & setters for enum variables:
        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        public String getHtmltarget() {
            return htmltarget;
        }

        public void setHtmltarget(String htmltarget) {
            this.htmltarget = htmltarget;
        }
    }
}
