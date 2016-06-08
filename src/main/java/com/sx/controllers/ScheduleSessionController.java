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

/**
 * Created by Mrtn on 27-5-2016.
 */
@Controller
public class ScheduleSessionController {

        //schedule_session template to edit sportSession Entity

        //instance of sessionRepository to access utility methods (database access)
        @Autowired
        private SessionService sessionService;

        //when a sportSession is found and clicked from previous template search_session this sportSession is inserted in the form
        @RequestMapping(value="schedulesession", method= RequestMethod.POST)
        public String scheduleSession(SportSession sportSession, Model model) {
            model.addAttribute("sportSession", sportSession);
            //separate date and time in 2 String objects and add to the Model:
            if (sportSession.getDateTime() != null) {
                model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(sportSession.getDateTime()));
                model.addAttribute("time", new SimpleDateFormat("HH:mm").format(sportSession.getDateTime()));
            }
            return "/schedule_session";
        }

        //save button persists sportSession Entity in the database using sportsession method and redirects to login (trainer_home)
        @RequestMapping("/savesession")
        public String save(SportSession sportSession, @RequestParam ("date") String date, @RequestParam("time") String time){
            try {
                sportSession.setDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date+" "+time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sessionService.save(sportSession);
            return "redirect:/login2";//without redirect: trainer_home will be loaded without the model variables...
        }
}
