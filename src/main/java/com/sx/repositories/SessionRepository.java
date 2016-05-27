package com.sx.repositories;

import com.sx.models.Customer;
import com.sx.models.SportSession;
import com.sx.models.Trainer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SessionRepository extends CrudRepository<SportSession, Integer> {
    public List<SportSession> findByTrainerAndCustomer(Trainer trainer, Customer customer);
}
