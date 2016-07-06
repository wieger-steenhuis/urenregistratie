package com.sx.service;

import com.sx.models.Customer;
import com.sx.models.Subscription;
import com.sx.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // TODO: Add query for Subscriptions for a Customer, in which still not-approved Sessions exist.
    // To be used for planning Sessions and for updating Customers

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