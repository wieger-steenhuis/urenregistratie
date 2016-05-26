package com.sx.service;

import com.sx.models.Customer;
import com.sx.models.Session;
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

    public void initSessions(Subscription subscription){
        //create sessions depending on SubscrType
        for (int i = 0; i < subscription.getSubscrType().getSessions(); i++) {
            Session session = new Session();
            session.setSubscription(subscription);//to enable manyToOne relation
            this.sessionRepository.save(session);
        }
    }

    public List<Session> findSessionsToSchedule(Trainer trainer, String search) {
        List<Session> sessionList = new ArrayList<>();
        for (Customer customer : customerService.searchNames(search, search)) {
            sessionList.addAll(this.sessionRepository.findByTrainerAndCustomer(trainer, customer));
        }
        return sessionList;
    }
}
