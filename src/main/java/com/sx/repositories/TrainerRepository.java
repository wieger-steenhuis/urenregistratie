package com.sx.repositories;

import com.sx.models.Trainer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainerRepository extends CrudRepository<Trainer, Integer> {
    public List<Trainer> findByFirstNameContainingOrLastNameContainingOrderByFirstNameAsc(String firstName, String lastName);
}
