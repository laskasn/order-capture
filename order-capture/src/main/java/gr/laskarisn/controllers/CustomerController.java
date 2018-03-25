package gr.laskarisn.controllers;

import org.springframework.web.bind.annotation.RestController;

import gr.laskarisn.entities.Customer;
import gr.laskarisn.repositories.CustomerRepository;

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
@RequestMapping("/customer")
public class CustomerController {
	
	
	private CustomerRepository customerRepository;
	

	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@RequestMapping("/count")
	@ResponseBody
    public Long count() {
		return customerRepository.count();
    }
	
	@RequestMapping("/list")
	@ResponseBody
    public List<Customer> list() {
		return customerRepository.findAll();
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
		Customer customer = customerRepository.findOne(uuid);
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = { "/create" }, consumes = "application/json", produces="application/json")
	public @ResponseBody ResponseEntity<Object> create(@RequestBody Customer customer) {
		try{
			customer = customerRepository.save(customer);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not create!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
	

	@RequestMapping(method = RequestMethod.PUT, value = { "/update" }, consumes = "application/json", produces="application/json")
	public @ResponseBody ResponseEntity<Object> update(@RequestBody Customer customer) {
		try{
			customer = customerRepository.save(customer);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not update!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
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
			customerRepository.delete(uuid);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully!");
	}
    
    
}
