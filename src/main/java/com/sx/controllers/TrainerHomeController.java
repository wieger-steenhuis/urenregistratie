/*
* Controller class for trainer_home.html
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

//controller class related to trainer_home.html. This view multiple trainer tasks and redirects to
@Controller
public class TrainerHomeController {
    //controller uses enum values (inner class) in a List
    //.values() returns an array of all available enum values
    private List<Trainer_Options> options = Arrays.asList(Trainer_Options.values());

    //getter & setter:
    public List<Trainer_Options> getOptions() {
        return options;
    }

    public void setOptions(List<Trainer_Options> options) {
        this.options = options;
    }


    //log in form on index.html sends data with th:action="@{/login}" in thymeleaf
    //TODO use security roles to redirect to admin_home or trainer_home requestMapping (security)
    //trainer_home is loaded with java object(s) through model.addAttributes("String identifier", object)
    //in this case the trainer tasks(enum) are loaded in the view

    @RequestMapping("/login2")
    public String trainerHome(Model model){
        model.addAttribute("trainer_opts", options);
        return "/trainer_home";
    }

    //the form on trainer_home uses th:action="@{/traineropts}" in thymeleaf
    //this form sends user input (chosen Trainer_Task) to this 'adress' that can be read with method=RM.GET
    @RequestMapping(value="traineropts", method= RequestMethod.GET)
    //user input is identified in thymeleaf with name="optionsListId" and is bound (@ModelAttribute) to chosen-parameter
    //because the Trainer_Options enum has a html target String, the chosen parameter takes care of redirecting to the right view
    public String choseTrainerAction(@ModelAttribute("optionsListId") Trainer_Options chosen) {
        return chosen.getHtmltarget();
    }

    //Enum Trainer_Options contains different trainer tasks to chose from
    //each constant contains a discription (to select in our views) and a specific html target to redirect to
    private enum Trainer_Options {

        //trainer tasks:
        SCHEDULE_SESSIONS("Lessen inplannen of wijzigen", "/search_session"),
        APPROVE_SESSIONS("Lessen goedkeuren", "/edit_trainer"),
        RAPPORTAGE("(Maand)rapportages maken", "/reporting");

        //enum variables:
        private String option;
        private String htmltarget;

        //contructor (implicitly private):
        Trainer_Options(String option, String htmltarget) {
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
