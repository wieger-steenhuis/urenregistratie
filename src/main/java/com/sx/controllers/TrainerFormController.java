package com.sx.controllers;

import com.sx.models.Trainer;
import com.sx.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TrainerFormController {
    //trainer_form template to edit or create Trainer Entity

    //instance of TrainerRepository to access utility methods (database access)
    @Autowired
    private TrainerService trainerService;

    //when a trainer is found and clicked from previous template edit_trainer this trainer is inserted in the form
    @RequestMapping(value="trainer", method= RequestMethod.POST)
    public String trainerSearch(Trainer trainer, Model model) {
        model.addAttribute("trainer", trainer);
        return "/trainer_form";
    }

    //when new trainer button is clicked from previous template edit_trainer a new customer is inserted in the form
    @RequestMapping("/newtrainer")
    public String newTrainer(Model model){
        Trainer trainer = new Trainer();
        model.addAttribute("trainer", trainer);
        return "/trainer_form";
    }

    //save button persists Trainer Entity in the database using trainerService method and redirects to login (admin_home)
    @RequestMapping("/saved")
    public String save(Trainer trainer){
        trainerService.save(trainer);
        return "redirect:/login";//without redirect: admin_honme will be loaded without the model variables...
    }
}
