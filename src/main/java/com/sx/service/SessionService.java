package com.sx.service;

import com.sx.models.Customer;
import com.sx.models.SportSession;
import com.sx.models.Subscription;
import com.sx.models.Trainer;
import com.sx.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            sportSession.setSubscription(subscription);//to enable manyToOne relation
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
}
