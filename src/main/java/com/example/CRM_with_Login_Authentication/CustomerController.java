package com.example.CRM_with_Login_Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomer(Model model, @RequestParam(required = false) String sort) {

//        get customers from DAO
        List<Customer> theCustomers = null;

//        check for sort filed
        if (sort != null) {
            int theSortField = Integer.parseInt(sort);
            theCustomers = customerService.getCustomers(theSortField);
        } else {
            theCustomers = customerService.getCustomers(1);
        }

//        add the customers to the model
        model.addAttribute("customers", theCustomers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showAddForm(Model model) {

//        create model attribute to bind form data
        Customer tempCustomer = new Customer();
        model.addAttribute("customer", tempCustomer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

//        save the customer
        customerService.saveCustomer(theCustomer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String updateCustomer(Model model, @RequestParam("customerId") int theId) {
//        get the customer from databse
        Customer theCustomer = customerService.getCustomer(theId);

//        set customer as a model attribute to pre populate the form
        model.addAttribute("customer", theCustomer);

//        send over to our form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String SearchCustomer(@RequestParam("theSearchName") String theSearchName,
                                 Model model) {
//        search the customers from service
        List<Customer> theCustomers = customerService.searchCustomer(theSearchName);

//        add customer to model
        model.addAttribute("customers", theCustomers);
        return "list-customers";
    }
}
