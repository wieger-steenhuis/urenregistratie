package com.sx.models;

import com.sx.formatters.DateWithoutTime;

import javax.persistence.*;


@Entity
//@Table(name="subscription") //don't need this, table name is equal to entity class...
public class Subscription {

    @Id
    //@Column(name="id") //don't need this, table name is equal to entity class...
    @GeneratedValue(strategy = GenerationType.AUTO) //Auto increment
    private int id;

    @DateWithoutTime
    private String startDate;

    @Enumerated(EnumType.STRING)
    private SubscrType subscrType;

    @ManyToOne
    //@JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    private Trainer trainer;

    private Subscription(){
        super();
    }

    public Subscription(SubscrType subscrType){
        super();
        this.subscrType = subscrType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

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

    public SubscrType getSubscrType() {
        return subscrType;
    }

    public void setSubscrType(SubscrType subscrType) {
        this.subscrType = subscrType;
    }

    @Override
    public String toString() {
        String t;
        if (trainer != null) t = this.getTrainer().getFirstName() + " " + this.getTrainer().getLastName();
        else t = "Nog geen trainer";
        return "Start: " + this.getStartDate() + ", " + t;
    }
}