package com.sx.controllers;

import com.sx.models.SportSession;
import com.sx.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class ApproveSessionController {

    @Autowired
    private SessionService sessionService;

    private SportSession thisSportSession;
    String wrongPin = "";

    @RequestMapping(value = "approvesession", method = RequestMethod.POST)
    public String approveSession(SportSession sportSession, Model model){
        thisSportSession = sportSession;
        model.addAttribute("sportSession", sportSession);
        return "/approve_session";
    }
    @RequestMapping(value = "customerapprove", method = RequestMethod.POST)
    public String customerApprove(@RequestParam ("pin") String pin, Model model){
        model.addAttribute("sportSession", thisSportSession);
        if (Integer.parseInt(pin)==Integer.parseInt(thisSportSession.getCustomer().getPin())){
            thisSportSession.setApproved(true);
            thisSportSession.setTimeApproved(new Date());
            sessionService.save(thisSportSession);
            return "redirect:/login2";
        }
        String wrongPin = "Foutieve Pincode, probeer opnieuw";
        model.addAttribute("wrongPin", wrongPin);
        return "/approve_session";
    }


}
