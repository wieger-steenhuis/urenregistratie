package com.sx.repositories;

import com.sx.models.Customer;
import com.sx.models.SportSession;
import com.sx.models.Trainer;
import com.sx.models.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;


public interface SessionRepository extends CrudRepository<SportSession, Integer> {
    public List<SportSession> findByTrainerAndCustomerOrderByDateTime(Trainer trainer, Customer customer);
    public List<SportSession> findByApprovedNotAndTrainerAndCustomerOrderByDateTime(boolean approved, Trainer trainer, Customer customer);
    public List<SportSession> findFirstByApprovedNotAndDateTimeNullAndTrainerAndCustomer(boolean approved, Trainer trainer, Customer customer);
    public List<SportSession> findByApprovedNotAndDateTimeNotNullAndTrainerAndCustomerOrderByDateTime(boolean approved, Trainer trainer, Customer customer);
    // DONE: Add queries (like the 2 above) to search for matching Subscription instead of Trainer and Customer
    public List<SportSession> findBySubscriptionAndDateTimeNullAndApprovedNot(Subscription subscription, boolean approved);
    public List<SportSession> findFirstBySubscriptionAndDateTimeNullAndApprovedNot(Subscription subscription, boolean approved);
    public List<SportSession> findBySubscriptionAndDateTimeNotNullAndApprovedNotOrderByDateTime(Subscription subscription, boolean approved);
    // queries for reporting
    public List<SportSession> findByTrainerAndDateTimeBetweenOrderByDateTime(Trainer trainer, Date from, Date to);
    public List<SportSession> findByApprovedNotAndTrainerAndDateTimeBetweenOrderByDateTime(boolean approved, Trainer trainer, Date from, Date to);
}
