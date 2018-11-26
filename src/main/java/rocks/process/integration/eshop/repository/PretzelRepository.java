package rocks.process.integration.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rocks.process.integration.eshop.domain.Pretzel;



@Repository
public interface PretzelRepository extends JpaRepository<Pretzel, Long> {
	public Pretzel findByName(@Param("name") String name); //Select statement: hole where email= eingabe)
}
