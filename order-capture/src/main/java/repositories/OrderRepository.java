package repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Customer;
import entities.Order;


@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, UUID> {
	
	
	
}

