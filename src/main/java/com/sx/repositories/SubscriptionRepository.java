package com.sx.repositories;

import com.sx.models.Customer;
import com.sx.models.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    public Subscription findByStartDate(String startDate);
    public List<Subscription> findByCustomer (Customer customer);
    // TODO: Add query for Subscriptions for a Customer, in which still not-approved Sessions exist.
    // To be used for planning Sessions and for updating Customers
}
