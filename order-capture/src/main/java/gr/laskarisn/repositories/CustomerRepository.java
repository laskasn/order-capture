package gr.laskarisn.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gr.laskarisn.entities.Customer;


@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
	
	@Query(value="SELECT c FROM customer c WHERE c.lastname LIKE %?1% OR c.firstname LIKE %?1%")
    public List<Customer> findAnywhereInNames(@Param("textToSearch") String textToSearch);
	
	
	
}

