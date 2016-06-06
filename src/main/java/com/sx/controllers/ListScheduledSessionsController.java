package com.sx.controllers;

import com.sx.service.SessionService;
import com.sx.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

/**
 * Created by Mrtn on 30-5-2016.
 */
public class ListScheduledSessionsController {

    //list_scheduled_sessions.html is loaded with the sessions for today, based on date=now & sessions in the database
    //the template shows a list of today's sessions

    //instance of TrainerRepository to access utility methods (database access)

    @Autowired
    private TrainerService trainerService;
    //instance of SessionRepository to access utility methods (database access)
    @Autowired
    private SessionService sessionService;


    // a list with the scheduled sessions for today's date is shown
    // entries can be selected to be approved by a customer
    //
    @RequestMapping("dayresults")
    public String daySessionSearch(Model model) {
        // todo : parameter trainer vervangen voor ingelogde trainer (securelogin?)
        model.addAttribute("thisdate", LocalDate.now() );

        model.addAttribute("searchresults", null); //, sessionService.findSessionsToSchedule(trainerService.searchNames("t","t").get(0), search));

        return "search_session";
    }
}
