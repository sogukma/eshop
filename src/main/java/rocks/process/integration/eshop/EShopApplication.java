
package rocks.process.integration.eshop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rocks.process.integration.eshop.domain.Customer;
import rocks.process.integration.eshop.domain.Pretzel;
import rocks.process.integration.eshop.repository.CustomerRepository;
import rocks.process.integration.eshop.repository.PretzelRepository;

/**
 * Hier wird Fake Brezel und Customer erstellt
 * 
 * @author Malik
 */
@SpringBootApplication
public class EShopApplication {
	@Autowired private PretzelRepository pretzelRepository;
	@Autowired private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(EShopApplication.class, args);
    }
    
    @PostConstruct
    public void init(){
    	Pretzel p = new Pretzel(); // id 1
    	p.setPrice(5);
    	p.setName("Butterbrezel");
    	p.setStockAmount(101);
    	p = pretzelRepository.save(p);
    	
    	Customer c = new Customer(); //id 2
    	c.setName("Schreiner");
    	c.setFirstname("Jumbo");
    	c.setAge(51);
    	c = customerRepository.save(c);

    }

	
}
