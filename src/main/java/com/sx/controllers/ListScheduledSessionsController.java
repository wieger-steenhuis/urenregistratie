package com.sx.controllers;

import com.sx.service.SessionService;
import com.sx.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ListScheduledSessionsController {

    //list_scheduled_sessions.html loads all SportSessions of today or a different date
    // the template genreates a list of a specific day's sessions

    //instance of TrainerRepository to access utility methods (database access)
    @Autowired
    private TrainerService trainerService;
    //instance of SessionRepository to access utility methods (database access)
    @Autowired
    private SessionService sessionService;


    //generate a list of sessions for the date picked by a user (Trainer)
    // interface HttpServletRequest's method getRemoteUser returns the username as a String
    @RequestMapping("somotherdaysession")
    public String otherDaySessions(@RequestParam ("date") String dateString, Model model, HttpServletRequest httpRequest) throws ParseException{
        if (!dateString.isEmpty()) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            System.out.println(date);
            model.addAttribute("thisdate", dateString);
            model.addAttribute("sportSessions", sessionService.findSessionsToApprove(trainerService.findByUsername(httpRequest.getRemoteUser()), date));
            return "/list_scheduled_sessions";
        } else {
            return listScheduledSessions(new Date(), model, httpRequest);
        }
    }

    //generate a list of sessions for today (date = now)
    // interface HttpServletRequest's method getRemoteUser returns the username as a String
    @RequestMapping("todayssessions")
    public String listScheduledSessions(Date date, Model model, HttpServletRequest httpRequest){
        date = new Date();
        model.addAttribute("thisdate", new SimpleDateFormat("yyyy-MM-dd").format(date));
        model.addAttribute("sportSessions", sessionService.findSessionsToApprove(trainerService.findByUsername(httpRequest.getRemoteUser()), date));
        return "/list_scheduled_sessions";
    }
}
