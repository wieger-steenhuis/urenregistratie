package com.sx.controllers;

import com.sx.formatters.DateWithoutTime;
import com.sx.models.SportSession;
import com.sx.models.Trainer;
import com.sx.service.SessionService;
import com.sx.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class ReportingController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private TrainerService trainerService;

    @RequestMapping(value="report", method= RequestMethod.POST)
    public String report(@RequestParam ("month") @DateWithoutTime Date month , Model model, HttpServletRequest hhtpRequest) {
        Trainer trainer = trainerService.findByUsername(hhtpRequest.getRemoteUser());
        List<SportSession> sessionsOfMonth = sessionService.findSessionsOfMonth(trainer, month);
        int NrOfApprovedSessions = 0;
        String trainerName = trainer.getFirstName() + " " + trainer.getLastName();
        for (SportSession sportSession : sessionsOfMonth) {
            if (sportSession.isApproved()) NrOfApprovedSessions++;
        }
        month.setTime(month.getTime()+(24*60*60*1000));//adds 1 day in miliseconds to correct wrong html output from GUI
        model.addAttribute("month", new SimpleDateFormat("MMMM yyyy").format(month));//MMMM displays full name of month
        model.addAttribute("NrOfApprovedSessions", NrOfApprovedSessions);
        model.addAttribute("sessionsOfMonth", sessionsOfMonth);
        model.addAttribute("trainerName", trainerName);
        return "/reporting";
    }
}
