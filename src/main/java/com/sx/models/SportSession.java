package com.sx.models;

import com.sx.formatters.DateWithTime;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class SportSession {

    @Id
    //@Column(name="id") //don't need this, table name is equal to entity class...
    @GeneratedValue(strategy = GenerationType.AUTO) //Auto increment
    private int id;

    private boolean approved;

    // Date and Time formats to print SportSessions in toString() method
    private static DateFormat sdf = new SimpleDateFormat("EEEE dd-MM-yyyy 'om' HH:mm 'uur'");
    private static DateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy 'om' HH:mm 'uur'");

    @DateWithTime
    private Date timeApproved;

    @DateWithTime
    private Date dateTime;

    @ManyToOne
    private Subscription subscription;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Trainer trainer;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Date getTimeApproved() {
        return timeApproved;
    }

    public void setTimeApproved(Date timeApproved) {
        this.timeApproved = timeApproved;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTime() {
        return this.dateTime = dateTime != null ? new Date(dateTime.getTime()) : null;
    }

    public void setDateTime(Date dateTime) {
        if (dateTime!=null) this.dateTime = new Date(dateTime.getTime());
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        Date time=this.getDateTime();
        if (time == null) {
            return customer.toString() + " - Niet ingepland";
        }
        else if (this.isApproved()) {
            return customer.toString() + " - Afgetekend op: " + sdf2.format( this.getTimeApproved());
        }
        else {
            return customer.toString() + " - " + sdf.format(time);
        }
    }
}