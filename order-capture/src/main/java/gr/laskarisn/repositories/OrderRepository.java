package gr.laskarisn.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.laskarisn.entities.Customer;
import gr.laskarisn.entities.Order;


@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, UUID> {
	
	
	
}

