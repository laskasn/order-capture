package gr.laskarisn.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gr.laskarisn.entities.Customer;
import gr.laskarisn.entities.Product;


@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, UUID> {
	 
	public List<Customer> findBySku(String sku);
	
}

