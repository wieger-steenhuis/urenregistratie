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

    // SubscriptionService needed to distinguish sessions from each subscription that matches the search
    // criteria: Trainer and Customer
    @Autowired
    private SubscriptionService subscriptionService;

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

    public List<SportSession> findOpenSessionsOfSubscription(Subscription subscription) {
        List<SportSession> sportSessionList = new ArrayList<>();
        sportSessionList.addAll(sessionRepository.findBySubscriptionAndDateTimeNullAndApprovedNot(subscription, true));
        return sportSessionList;
    }

    public List<SportSession> findPendingSessionsOfSubscription(Subscription subscription) {
        List<SportSession> sportSessionList = new ArrayList<>();
        sportSessionList.addAll(sessionRepository.findBySubscriptionAndDateTimeNotNullAndApprovedNot(subscription, true));
        return sportSessionList;
    }

    public List<SportSession> findSessionsToSchedule(Trainer trainer, String search) {
        List<SportSession> sportSessionList = new ArrayList<>();
        for (Customer customer : customerService.searchNames(search, search)) {
            // DONE: first find Subscriptions that match the Trainer and Customer, next search sessions belonging to each Subscription
            for (Subscription subscription : subscriptionService.findByCustomerTrainerPendingSession(trainer, customer)){
            // DONE: The next 2 queries must be rewritten to search for matching Subscription instead (update Interface as well!)
            // TODO: For not planned sessions within a Subscription the total must be presented as well
                sportSessionList.addAll(this.sessionRepository.findFirstBySubscriptionAndDateTimeNullAndApprovedNot(subscription, true));
                sportSessionList.addAll(this.sessionRepository.findBySubscriptionAndDateTimeNotNullAndApprovedNot(subscription, true));
            }
        }
        return sportSessionList;
    }
    public List<SportSession> findSessionsToApprove(Trainer trainer, Date dateTime) {
        Calendar cal = Calendar.getInstance();
//        System.out.println("NOW =" +cal.getTime());
        cal.setTime(dateTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date from = cal.getTime();
//        System.out.println("FROM ="+from);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date to = cal.getTime();
//        System.out.println("TO ="+to);
        return this.sessionRepository.findByApprovedNotAndTrainerAndDateTimeBetweenOrderByDateTime(true, trainer, from, to);
    }

    public List<SportSession> findSessionsOfMonth(Trainer trainer, Date dateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date from = cal.getTime();
//        System.out.println("FROM ="+from);
        cal.add(Calendar.MONTH, 1);
        Date to = cal.getTime();
//        System.out.println("TO ="+to);
        return this.sessionRepository.findByTrainerAndDateTimeBetweenOrderByDateTime(trainer, from, to);
    }
}
