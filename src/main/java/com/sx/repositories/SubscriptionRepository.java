package com.sx.repositories;

import com.sx.models.Customer;
import com.sx.models.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    public Subscription findByStartDate(String startDate);
    public List<Subscription> findByCustomer (Customer customer);
}
