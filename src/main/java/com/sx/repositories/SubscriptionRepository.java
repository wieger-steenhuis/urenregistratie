package com.sx.repositories;

import com.sx.models.Customer;
import com.sx.models.Subscription;
import com.sx.models.Trainer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    public Subscription findByStartDate(String startDate);
    public List<Subscription> findByCustomer (Customer customer);
    // DONE: Add query for Subscriptions for a Customer and Trainer
    // To be used for planning Sessions
    public List<Subscription> findByTrainerAndCustomer (Trainer trainer, Customer customer);
}
