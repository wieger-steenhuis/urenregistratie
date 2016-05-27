package com.sx.models;

import javax.persistence.*;

@Entity
public class SportSession {

    @Id
    //@Column(name="id") //don't need this, table name is equal to entity class...
    @GeneratedValue(strategy = GenerationType.AUTO) //Auto increment
    private int id;
    private String date;

    @ManyToOne
    private Subscription subscription;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Trainer trainer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return customer + " " + date;
    }
}