package com.sx.controllers;

import com.sx.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EditTrainerController {
    //edit_trainer.html is first loaded without any data
    //the template shows a button to create a new trainer (just sends request to 'newtrainer')
    //and has a text field and search button that interacts with trainerSearch method

    //instance of TrainerRepository to access utility methods (database access)
    @Autowired
    private TrainerService trainerService;

    //the search form sends String data to 'results' with an identifier 'trainerSearch'
    //'trainerSearch' is the parameter for searching the trainer database with 'trainerService' method
    //'searchresults' is a list of trainers that match the searchcriteria and is addded to this view
    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String customerSearch(@ModelAttribute("trainerSearch") String search, Model model) {
        model.addAttribute("searchresults", trainerService.searchNames(search, search));
        return "/edit_trainer";
    }
}