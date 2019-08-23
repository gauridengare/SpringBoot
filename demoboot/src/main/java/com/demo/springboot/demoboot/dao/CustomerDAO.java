package com.demo.springboot.demoboot.dao;

import java.util.List;

import com.demo.springboot.demoboot.model.Customer;

public interface CustomerDAO {
public List<Customer> findAll();
public void saveCustomer(Customer customer);
public void deleteCustomer(int customerId);
public Customer getCustomer(int theId); 
}
