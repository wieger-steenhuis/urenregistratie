package com.sx.service;

import com.sx.models.Session;
import com.sx.models.Subscription;
import com.sx.models.Trainer;
import com.sx.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Session> searchSessionsByCustomerName(String firstName, String lastName, Trainer trainer){

        customerService.searchNames(firstName,lastName);
        return null; // to do: for each customer result uit searchnames 1: check of er sub is bij trainer -> 2: sessies teruggeven
                    //  in sessie een trainer en een klant zetten.
}
