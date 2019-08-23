package com.demo.springboot.demoboot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.demoboot.dao.CustomerDAO;
import com.demo.springboot.demoboot.model.Customer;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerDAO customer;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers()
	{
		return customer.findAll();
		
	}
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer)
	{
		theCustomer.setId(0);
		 customer.saveCustomer(theCustomer);
		 return theCustomer;
	}
	
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable int customerId)
	{
		return customer.getCustomer(customerId);
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId)
	{
		
		 customer.deleteCustomer(customerId);
		return "customer deleted id: "+customerId;
	}
	
	
}

