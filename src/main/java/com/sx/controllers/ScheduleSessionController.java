package com.sx.controllers;

import com.sx.models.SportSession;
import com.sx.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mrtn on 27-5-2016.
 */
@Controller
public class ScheduleSessionController {

        //schedule_session template to edit sportSession Entity

        //instance of sessionRepository to access utility methods (database access)
        @Autowired
        private SessionService sessionService;

        private SimpleDateFormat sdtf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        //when a sportSession is found and clicked from previous template search_session this sportSession is inserted in the form
        @RequestMapping(value="schedulesession", method= RequestMethod.POST)
        public String scheduleSession(SportSession sportSession, Model model) {
            boolean success = true;
            model.addAttribute("succeeded", success);
            model.addAttribute("sportSession", sportSession);
            model.addAttribute("phone", sportSession.getCustomer().getPhoneNr());
            // needed to check Session date >= Subscription start date
//            model.addAttribute("subscriptionStartDate", sportSession.getSubscription().getStartDate());
            //separate date and time in 2 String objects and add to the Model:
            if (sportSession.getDateTime() != null) {
                model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(sportSession.getDateTime()));
                model.addAttribute("time", new SimpleDateFormat("HH:mm").format(sportSession.getDateTime()));
            }
            return "/schedule_session";
        }

        //save button persists sportSession Entity in the database using sportsession method and redirects to login (trainer_home)
        @RequestMapping("/savesession")
        public String save(SportSession sportSession, @RequestParam ("date") String date, @RequestParam("time") String time, Model model){
            String subcriptionDate = sportSession.getSubscription().getStartDate();
            boolean success = false;
            try {
                // Check of session date niet vóór start abonnement ligt
                Date subsDate = sdtf.parse(subcriptionDate+" 00:00");
                Date checkSessDate = sdtf.parse(date+" 00:01");
                Date sessDate = sdtf.parse(date+" "+time);
                if(subsDate.before(checkSessDate)){
                    success = true;
                    sportSession.setDateTime(sessDate);
                }
                else {
//                    System.out.println("return to schedule session page");
                    success = false;
                    model.addAttribute("succeeded", success);
                    model.addAttribute("date", date);
                    model.addAttribute("time", time);
                    return "/schedule_session";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sessionService.save(sportSession);
            return "redirect:/sesresults";//without redirect: search_session will be loaded without the model variables...
        }
}
