package com.sx.service;

import com.sx.models.Customer;
import com.sx.models.SportSession;
import com.sx.models.Subscription;
import com.sx.models.Trainer;
import com.sx.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CustomerService customerService;

    public SportSession save(SportSession sportSession){
        return this.sessionRepository.save(sportSession);
    }

    public void initSessions(Subscription subscription){
        //create sessions depending on SubscrType
        for (int i = 0; i < subscription.getSubscrType().getSessions(); i++) {
            SportSession sportSession = new SportSession();
            sportSession.setSubscription(subscription);//to enable manyToOne relations
            sportSession.setCustomer(subscription.getCustomer());
            sportSession.setTrainer(subscription.getTrainer());
            this.sessionRepository.save(sportSession);
        }
    }

    public List<SportSession> findSessionsToSchedule(Trainer trainer, String search) {
        List<SportSession> sportSessionList = new ArrayList<>();
        for (Customer customer : customerService.searchNames(search, search)) {
            sportSessionList.addAll(this.sessionRepository.findByTrainerAndCustomer(trainer, customer));
        }
        return sportSessionList;
    }
    public List<SportSession> findSessionsToApprove(Trainer trainer, Date dateTime) {
        Calendar cal = Calendar.getInstance();
        System.out.println("NOW =" +cal.getTime());
        cal.setTime(dateTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date from = cal.getTime();
        System.out.println("FROM ="+from);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date to = cal.getTime();
        System.out.println("TO ="+to);
        return this.sessionRepository.findByTrainerAndDateTimeBetween(trainer, from, to);
    }
}
