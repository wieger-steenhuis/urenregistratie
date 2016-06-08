package com.sx.repositories;

import com.sx.models.Customer;
import com.sx.models.SportSession;
import com.sx.models.Trainer;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;


public interface SessionRepository extends CrudRepository<SportSession, Integer> {
    public List<SportSession> findByTrainerAndCustomerOrderByDateTime(Trainer trainer, Customer customer);
    public List<SportSession> findByApprovedNotAndTrainerAndCustomerOrderByDateTime(boolean approved, Trainer trainer, Customer customer);
    public List<SportSession> findByTrainerAndDateTimeBetweenOrderByDateTime(Trainer trainer, Date from, Date to);
    public List<SportSession> findByApprovedNotAndTrainerAndDateTimeBetweenOrderByDateTime(boolean approved, Trainer trainer, Date from, Date to);
}
