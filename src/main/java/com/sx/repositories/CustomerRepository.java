package com.sx.repositories;

import com.sx.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    public List<Customer> findByFirstNameContainingOrLastNameContainingOrderByFirstNameAsc(String firstName, String lastName);
}
