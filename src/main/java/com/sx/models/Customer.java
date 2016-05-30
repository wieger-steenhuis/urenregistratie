package com.sx.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.validation.constraints.NotNull;// ASDJ: Not used, required field in html form instead
//import javax.validation.constraints.Size;//ASDJ: Not used anymore, check in html form instead

@Entity
//@Table(name="customer") //don't need this, table name is equal to entity class...
public class Customer {
//    private static final int PINLENGTH = 5;// not used anymore

    @Id
    //@Column(name="id") //don't need this, table name is equal to entity class...
    @GeneratedValue(strategy= GenerationType.AUTO) // Auto generate ID's (auto increment in MySQL)
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNr;
    private String eMail;
//    @Size(min = PINLENGTH, max = PINLENGTH)
    private String pin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
            return this.firstName+" "+this.lastName;
        }
}
