package gr.laskarisn.controllers;

import org.springframework.web.bind.annotation.RestController;

import gr.laskarisn.entities.Product;

import gr.laskarisn.repositories.ProductRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
	
	
	private ProductRepository productRepository;
	

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	
	@RequestMapping(method = RequestMethod.GET, value = { "/count" })
	@ResponseBody
    public Long count() {
		return productRepository.count();
    }
	
	@RequestMapping(method = RequestMethod.GET, value = { "/list" })
	@ResponseBody
    public List<Product> list() {
		return productRepository.findAll();
    }
	
	@RequestMapping(method = RequestMethod.GET, value = { "/get/{id}" }, produces="application/json")
	public @ResponseBody ResponseEntity<Object> getById(@PathVariable("id") String id) {
		UUID uuid = null;
		try{
			uuid = UUID.fromString(id);
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("That's not a valid uuid");
		}
		Product product = productRepository.findOne(uuid);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = { "/create" }, consumes = "application/json", produces="application/json")
	public @ResponseBody ResponseEntity<Object> create(@RequestBody Product product) {
		try{
			product = productRepository.save(product);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not create!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	

	@RequestMapping(method = RequestMethod.PUT, value = { "/update" }, consumes = "application/json", produces="application/json")
	public @ResponseBody ResponseEntity<Object> update(@RequestBody Product product) {
		try{
			product = productRepository.save(product);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not update!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = { "/delete/{id}" }, produces="application/json")
	public @ResponseBody ResponseEntity<Object> delete(@PathVariable String id) {
		
		UUID uuid = null;
		try{
			uuid = UUID.fromString(id);
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("That's not a valid uuid");
		}
		
		try{
			productRepository.delete(uuid);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully!");
	}
	
	
    
    
}
