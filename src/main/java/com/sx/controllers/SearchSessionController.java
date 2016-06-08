package com.sx.controllers;

import com.sx.service.SessionService;
import com.sx.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Mrtn on 25-5-2016.
 */
@Controller
public class SearchSessionController {
    //search_session.html is first loaded without any data
    //the template shows a text field and search button that interacts with sessionSearch method

    //instance of TrainerRepository to access utility methods (database access)
    @Autowired
    private TrainerService trainerService;
    //instance of SessionRepository to access utility methods (database access)
    @Autowired
    private SessionService sessionService;


    //the search form sends String data to 'sesresults' with an identifier 'customerSessionSearch'
    //'customerSessionSearch' is the parameter for searching the session database with 'sessionSearch' method
    //'searchresults' is a list of sessions that match the searchcriteria and is addded to this view
    //Parameters are Trainer and Customer, where Trainer represents the logged in user
    // interface HttpServletRequest's method getRemoteUser returns the username as a String
    @RequestMapping(value = "sesresults", method = RequestMethod.GET)
    public String customerSessionSearch(@ModelAttribute("customerSessionSearch") String search, Model model, HttpServletRequest httpRequest) {
        model.addAttribute("searchresults", sessionService.findSessionsToSchedule(trainerService.findByUsername(httpRequest.getRemoteUser()), search));
        return "search_session";
    }
}

