package com.sx.repositories;

import com.sx.models.Customer;
import com.sx.models.Session;
import com.sx.models.Trainer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SessionRepository extends CrudRepository<Session, Integer> {
    public List<Session> findByTrainerAndCustomer(Trainer trainer, Customer customer);
}
