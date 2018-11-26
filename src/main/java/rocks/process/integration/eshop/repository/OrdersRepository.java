package rocks.process.integration.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rocks.process.integration.eshop.domain.Orders;



@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {

}
