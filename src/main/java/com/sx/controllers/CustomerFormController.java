package com.sx.controllers;

import com.sx.models.Customer;
import com.sx.service.CustomerService;
import com.sx.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerFormController {
    //customer_form template to edit or create Customer Entity and to acces or create it's subscriptions

    //instance of CustomerRepository to access utility methods (database access)
    @Autowired
    private CustomerService customerService;

    //instance of SubscriptionService to access utility methods (database access)
    @Autowired
    private SubscriptionService subscriptionService;

    //when a customer is found and clicked from previous template edit_customer this customer is inserted in the form
    //and its subscriptions (if any) will load using subscriptionService method
    @RequestMapping(value="customer", method= RequestMethod.POST)
    public String editCustomer(Customer customer, Model model) {
        model.addAttribute("customer", customer);
        model.addAttribute("subscriptions", subscriptionService.findByCustomer(customer));
        return "/customer_form";
    }

    //when new customer button is clicked from previous template edit_customer a new customer is inserted in the form
    @RequestMapping("/newcustomer")
    public String newCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "/customer_form";
    }

    //save button persists Customer Entity in the database using customerService method
    @RequestMapping("/save")
    public String save(Customer customer){
        customerService.save(customer);
        return "redirect:/login";//without redirect: admin_honme will be loaded without the model variables...
    }
}
