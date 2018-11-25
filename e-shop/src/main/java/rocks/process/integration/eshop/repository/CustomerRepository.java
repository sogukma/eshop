package rocks.process.integration.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rocks.process.integration.eshop.domain.Customer;




@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	public Customer findByName(@Param("name") String name); //Select statement: hole where email= eingabe)
}
