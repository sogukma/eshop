/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.integration.eshop.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rocks.process.integration.eshop.api.CustomerAPI;
import rocks.process.integration.eshop.domain.Customer;

import rocks.process.integration.eshop.domain.Orders;
import rocks.process.integration.eshop.repository.CustomerRepository;

import rocks.process.integration.eshop.repository.OrdersRepository;

import java.util.UUID;


/**
 * 
 *
 */
@Component
public class CustomerAPIImpl implements CustomerAPI {

	
    private Logger logger = LoggerFactory.getLogger(CustomerAPIImpl.class);

	@Autowired
	public CustomerRepository customerRepository;
	@Autowired
	public OrdersRepository ordersRepository;
	
    @Override
    public Customer findCustomer(String customerId) {
          
        Customer c = customerRepository.findById(Long.parseLong(customerId)).get();
        return c;
        
    }

    @Override
    public Customer editLoyaltyBalance(String customerId) {
        
    	Customer c = customerRepository.findById(Long.parseLong(customerId)).get(); //id 2 Customer
      	//Nach Zahlung wird prinzipiell Loyality Points um 10 vermindert
    	if(c.getLoyalityPoints()>= 10)
    	{
    	c.setLoyalityPoints(c.getLoyalityPoints()- 10);
    	}
    	customerRepository.save(c);
    	
    	logger.info("customerId: " + customerId + ". points " + c.getLoyalityPoints());
    	return c;
    }
}
