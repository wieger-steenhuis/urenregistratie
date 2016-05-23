package com.sx.controllers;

import com.sx.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EditCustomerController {
    //edit_customer.html is first loaded without any data
    //the template shows a button to create a new customer (just sends request to 'newcustomer')
    //and has a text field and search button that interacts with customerSearch method

    //instance of CustomerService to access utility methods (database access)
    @Autowired
    private CustomerService customerService;

    //the search form sends String data to 'result' with an identifier named 'customerSearch'
    //'customerSearch' is the parameter for searching the customer database with 'customerService' method
    //'searchresults' is a list of customers that match the searchcriteria and is addded to this view
    @RequestMapping(value="result", method= RequestMethod.GET)
    public String customerSearch(@ModelAttribute("customerSearch") String search, Model model) {
        model.addAttribute("searchresults", customerService.searchNames(search, search));
        return "/edit_customer";
    }
}