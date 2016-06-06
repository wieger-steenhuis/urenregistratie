package com.sx.controllers;

import com.sx.models.Customer;
import com.sx.models.SubscrType;
import com.sx.models.Subscription;
import com.sx.models.Trainer;
import com.sx.service.CustomerService;
import com.sx.service.SubscriptionService;
import com.sx.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;


@Controller
public class SubscriptionFormController {

    //'instances' of repository interfaces to access CRUD functionality (database access)
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TrainerService trainerService;

    //to add data to the model for this view outside of it's methods use @ModelAttribute

    //a complete list of all trainers is added to select a trainer with a subscription
    @ModelAttribute("trainers")
    public List<Trainer> populateTrainers() {
        return (List<Trainer>)trainerService.findAll();
    }

    //a list of subscriptionTypes is added to select a subscription type (new subscriptions only)
    @ModelAttribute("types")
    public List<SubscrType> populateTypes(){
        return Arrays.asList(SubscrType.values());
    }

    //when an existing subscription is clicked from previous template customer_form this subscription is inserted in the form
    @RequestMapping(value="/subscription", method= RequestMethod.POST)
    public String subscriptionSearch(@RequestParam (value = "subscription") Subscription subscription, Model model) {
        model.addAttribute("subscription", subscription);
        return "/subscription_form";
    }

    //when new subscription button is clicked from previous template customer_form a new subscription is inserted in the form
    //customer is saved first to persist edited fields and to obtain a customer id in case of new customer
    @RequestMapping(value="/newsubscription", method=RequestMethod.POST)
    public String newSubscription(Customer customer, Model model) {
        customer = customerService.save(customer);
        Subscription subscription = new Subscription(SubscrType.TWENTYFOUR);
        subscription.setCustomer(customer);
        model.addAttribute("subscription", subscription);
        return "/subscription_form";
    }

    //save button persists Subscription Entity in the database using subscriptionService method and
    // redirects to customer_form with subscription.customer data
    @RequestMapping(value = "/save_subscription", method=RequestMethod.POST)
    public String saveSubscription(Subscription subscription, Model model){
        subscription = subscriptionService.save(subscription);
        model.addAttribute("customer", customerService.findOne(subscription.getCustomer().getId()));
        model.addAttribute("subscriptions", subscriptionService.findByCustomer(subscription.getCustomer()));
        return "/customer_form";
    }
}