package com.example.CRM_with_Login_Authentication;

import java.util.List;

public interface CustomerDAO {


    List<Customer> getCustomers(int theSortField);

    void saveCustomer(Customer tempCustomer) ;

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);

    List<Customer> searchCustomers(String theSearchName);
}
