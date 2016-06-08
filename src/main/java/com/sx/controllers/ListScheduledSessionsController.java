package com.sx.controllers;

import com.sx.service.SessionService;
import com.sx.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ListScheduledSessionsController {

    //to add data to the model for this view outside of it's methods use @ModelAttribute

    //list_scheduled_sessions.html is loaded with the sessions for today, based on date=now & sessions in the database
    //the template shows a list of today's sessions

    //instance of TrainerRepository to access utility methods (database access)
    @Autowired
    private TrainerService trainerService;
    //instance of SessionRepository to access utility methods (database access)
    @Autowired
    private SessionService sessionService;


//    @ModelAttribute("thisdate")
//    public String today() {
//        return new Date().toString();
//    }

//    @ModelAttribute("sportSessions")
//    public List<SportSession> populateSessions() {
//        return
//    }



    @RequestMapping("somotherdaysession")
    public String otherDaySessions(@RequestParam ("date") String dateString, Model model) throws ParseException{
        if (!dateString.isEmpty()) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            System.out.println(date);
            model.addAttribute("thisdate", dateString);
            model.addAttribute("sportSessions", sessionService.findSessionsToApprove(trainerService.searchNames("t", "t").get(0), date));
            return "/list_scheduled_sessions";
        } else {
            return listScheduledSessions(new Date(), model);
        }
    }

    @RequestMapping("todayssessions")
    public String listScheduledSessions(Date date, Model model){
        date = new Date();
        model.addAttribute("thisdate", new SimpleDateFormat("yyyy-MM-dd").format(date));
        model.addAttribute("sportSessions", sessionService.findSessionsToApprove(trainerService.searchNames("t","t").get(0), date));
        return "/list_scheduled_sessions";
    }
//     a list with the scheduled sessions for today's date is shown
//     entries can be selected to be approved by a customer
//    @RequestMapping("dayresults")
//    public String daySessionSearch(Model model) {
//        // todo : parameter trainer vervangen voor ingelogde trainer (securelogin?)
//        model.addAttribute("searchresults", null); //, sessionService.findSessionsToSchedule(trainerService.searchNames("t","t").get(0), search));
//        return "search_session";
//    }
}
