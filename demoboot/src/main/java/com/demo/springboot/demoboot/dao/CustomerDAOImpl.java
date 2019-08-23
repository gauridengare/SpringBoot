package com.demo.springboot.demoboot.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springboot.demoboot.model.Customer;

//specialization of the @Component
//act as a database repository
@Repository
public class CustomerDAOImpl implements CustomerDAO{

	
	//EntityManager(from JPA) - using this we can interact with persistance context
	//similar to Hibernate sessionFactory
	//wrapper for hibernate session object
	private EntityManager entityManager ;
	//this annotation is optional as there is just one constructor
		@Autowired  
		public CustomerDAOImpl(EntityManager entityManager) {
			super();
			this.entityManager = entityManager;
		}
		
		
	@Override
	@Transactional //if we use this annotation we dont have to manually start and commit transaction
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		Session currentSession=entityManager.unwrap(Session.class);
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer",
											Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				System.out.println(customers);
		// return the results		
		return customers;
		
		
		
	}


	@Override
	public void saveCustomer(Customer customer) {
		Session currentSession=entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(customer);
		
	}


	@Override
	@Transactional
	public void deleteCustomer(int customerId) {
		Session currentSession=entityManager.unwrap(Session.class);
		Query theQuery = 
				currentSession.createQuery("delete Customer where id =: customerId");
		theQuery.setParameter("customerId", customerId);
		
		theQuery.executeUpdate();
		
	}


	@Override
	public Customer getCustomer(int theId) {
		Session currentSession=entityManager.unwrap(Session.class);
     Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	
	}

	

}
