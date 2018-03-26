package gr.laskarisn.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.laskarisn.entities.Product;
import gr.laskarisn.repositories.ProductRepository;

@Service
public class ProductService {

	
	@Autowired
	ProductRepository productRepository;
	
	
	public Long countProducts() {
		return productRepository.count();
	}
	
	
	public List<Product> listAllProducts(){
		return productRepository.findAll();
	}
	
	public Product getProductByID(UUID uuid) {
		return productRepository.findOne(uuid);
	}
	
	public Product createProduct(Product product) throws Exception {
		if(product.getId()!=null)
			throw new Exception("Creating a product with an existing id is not permitted.");
		return productRepository.save(product);
	}
	
	public Product updateProduct(Product product) throws Exception {
		if(product.getId()==null)
			throw new Exception("Trying to update a product without an ID. That's not allowed!");
		return productRepository.save(product);
	}
	
	public void deleteProduct(UUID uuid) {
		productRepository.delete(uuid);
	}
	
	
	
}
