package com.sx.controllers;

import com.sx.formatters.DateWithoutTime;
import com.sx.models.SportSession;
import com.sx.service.SessionService;
import com.sx.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by unknown on 8-6-2016.
 */

@Controller
public class ReportingController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private TrainerService trainerService;

    @RequestMapping(value="report", method= RequestMethod.POST)
    public String report(@RequestParam ("month") @DateWithoutTime Date month , Model model) {
        List<SportSession>sessionsOfMonth = sessionService.findSessionsOfMonth(trainerService.searchNames("t", "t").get(0), month);
        int NrOfApporvedSessions = 0;
        for (SportSession sportSession : sessionsOfMonth){
            if (sportSession.isApproved()) NrOfApporvedSessions++;
        }
        model.addAttribute("month", new SimpleDateFormat("MM-yyyy").format(month));
        model.addAttribute("NrOfApporvedSessions", NrOfApporvedSessions);
        model.addAttribute("sessionsOfMonth", sessionsOfMonth);
        return "/reporting";
    }
}
