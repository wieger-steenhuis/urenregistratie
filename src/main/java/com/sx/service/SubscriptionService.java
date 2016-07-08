package com.sx.service;

import com.sx.models.Customer;
import com.sx.models.Subscription;
import com.sx.models.Trainer;
import com.sx.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SessionService sessionService;

    public Subscription findByStartDate(String startDate){
        return this.subscriptionRepository.findByStartDate(startDate);
    }

    public List<Subscription> findByCustomer (Customer customer){
        return this.subscriptionRepository.findByCustomer(customer);
    }

//     TODO: Add query for Subscriptions for a Customer, in which still not-approved Sessions exist.
//     To be used for planning Sessions and for updating Customers
    public List<Subscription> findByCustomerTrainerPendingSession (Trainer trainer, Customer customer){
        List<Subscription> helpSubsList = new ArrayList<Subscription>();
        helpSubsList.addAll(this.subscriptionRepository.findByTrainerAndCustomer(trainer, customer));
        List<Subscription> subsList = new ArrayList<Subscription>();
        for (Subscription subs : helpSubsList) {
            if(sessionService.findPendingSessionsOfSubscription(subs).size() != 0 ||
                    sessionService.findOpenSessionsOfSubscription(subs).size() != 0)
                subsList.add(subs);}
        return subsList;
}
//     DONE: Add query for Subscriptions for a Customer and Trainer, in which still not-planned Sessions exist.
//     To be used for planning Sessions
     public List<Subscription> findByCustomerTrainerOpenSession (Trainer trainer, Customer customer){
         List<Subscription> helpSubsList = new ArrayList<Subscription>();
         helpSubsList.addAll(this.subscriptionRepository.findByTrainerAndCustomer(trainer, customer));
         List<Subscription> subsList = new ArrayList<Subscription>();
         for (Subscription subs : helpSubsList) {
             if(sessionService.findOpenSessionsOfSubscription(subs).size() != 0)
                 subsList.add(subs);}
         return subsList;
     }


    public Subscription save(Subscription subscription) {
        //whenever a new subscription is saved initSessions creates session objects (depending on subscrType)
        if (subscription.getId() == 0) {
            subscription = this.subscriptionRepository.save(subscription); //reassign subscr. after save to obtain id from SQL table...
            sessionService.initSessions(subscription); //because this subscr. must have id to persist related sessions
            return subscription;
        } else {
            return this.subscriptionRepository.save(subscription);
        }
    }
}