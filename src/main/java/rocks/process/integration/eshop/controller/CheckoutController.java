/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.integration.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rocks.process.integration.eshop.business.client.CheckoutAdapter;
import rocks.process.integration.eshop.business.message.OrderMessage;
import rocks.process.integration.eshop.domain.Customer;
import rocks.process.integration.eshop.domain.Orders;
import rocks.process.integration.eshop.domain.Pretzel;
import rocks.process.integration.eshop.repository.CustomerRepository;
import rocks.process.integration.eshop.repository.PretzelRepository;
import rocks.process.integration.eshop.service.OrdersService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class CheckoutController {

    @Autowired private CheckoutAdapter checkoutAdapter;
	@Autowired private OrdersService ordersService;
	@Autowired
	public PretzelRepository pretzelRepository;
	@Autowired
	public CustomerRepository customerRepository;
    
    
    

    @GetMapping(path = "/order", produces = "text/plain")
	public ResponseEntity<String> createOrder() {

		Customer c = customerRepository.findById(2L).get(); //id 2 Customer
		Pretzel p = pretzelRepository.findById(1L).get(); //id 1 Pretzel
    	
		
		Orders order = ordersService.orderPretzel(c.getCustomerId()+"", p.getPretzelId()+"", 100);
		
		//loyalitätspunkt wird gesteigert mit einem Zehntel der Totalkosten der Bestellung
		c.setLoyalityPoints(c.getLoyalityPoints() + (int) order.getTotalCost()/10);
		
		List<OrderMessage.OrderItem> orderItems = new ArrayList<>();
		orderItems.add(new OrderMessage.OrderItem("1", p.getPretzelId()+"", order.getAmount())); // item 1, productId 1, quantity 100
		
		Double charingAmountOfMoney;
		// Zahlungsbetrag wird von loyalitätspunkte abgezogen, sofern kleiner als Zahlunsbetrag
		if(order.getTotalCost()>= c.getLoyalityPoints()) {
		charingAmountOfMoney = order.getTotalCost()-c.getLoyalityPoints();
		}
		else
		{
			charingAmountOfMoney = order.getTotalCost();
		}
		OrderMessage orderMessage = new OrderMessage(order.getOrderId()+"", c.getCustomerId()+"", 1D, 1, orderItems, null, null, null, "Order Placed", p.getStockAmount(), charingAmountOfMoney);		
		checkoutAdapter.doOrder(orderMessage);
		return ResponseEntity.ok(orderMessage.toString());
	}
    
}
