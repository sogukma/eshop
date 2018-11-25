package rocks.process.integration.eshop.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rocks.process.integration.eshop.domain.Customer;
import rocks.process.integration.eshop.domain.Orders;
import rocks.process.integration.eshop.domain.Pretzel;
import rocks.process.integration.eshop.repository.CustomerRepository;
import rocks.process.integration.eshop.repository.OrdersRepository;
import rocks.process.integration.eshop.repository.PretzelRepository;





@Service
public class OrdersService {

	@Autowired
	public OrdersRepository ordersRepository;
	@Autowired
	public PretzelRepository pretzelRepository;
	@Autowired
	public CustomerRepository customerRepository;
	
	public Orders orderPretzel(String costumerName, String pretzelName, int amount)
	{
	
		
		Customer c = customerRepository.findById(2L).get(); //id 2 Customer
		Pretzel p = pretzelRepository.findById(1L).get(); //id 1 Pretzel
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		Orders o = new Orders();
		o.setAmount(amount);
		o.setCustomer(c);
		o.setPretzel(p);
		o.setStatus("ordered");
		o.setTotalCost(p.getPrice()* o.getAmount());
		o.setOrderDate(dateFormat.format(date));
		ordersRepository.save(o);

		p.setStockAmount(p.getStockAmount() - o.getAmount());
		pretzelRepository.save(p);
		
		int intCost = (int) o.getTotalCost();
		c.setLoyalityPoints(c.getLoyalityPoints() + intCost/10);
		customerRepository.save(c);
		return o;
	}

}
