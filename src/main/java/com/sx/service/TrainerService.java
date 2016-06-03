package com.sx.service;

import com.sx.models.Trainer;
import com.sx.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    public List<Trainer> searchNames(String firstName, String lastName){
        return this.trainerRepository.findByFirstNameContainingOrLastNameContainingOrderByFirstNameAsc(firstName, lastName);
    }

    public Trainer save(Trainer trainer){
        return this.trainerRepository.save(trainer);
    }
    Trainer findByUsername(String name){return this.trainerRepository.findByUsername(name);}

    public Iterable<Trainer> findAll(){
        return this.trainerRepository.findAll();
    }
}