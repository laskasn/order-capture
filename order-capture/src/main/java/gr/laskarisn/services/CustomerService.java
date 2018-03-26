package gr.laskarisn.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gr.laskarisn.entities.Customer;
import gr.laskarisn.entities.Product;
import gr.laskarisn.messengers.PlainCustomer;
import gr.laskarisn.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	public Long countAllCustomers() {
		return customerRepository.count();
	}
	
	public List<Customer> listAllCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer getCustomerByID(UUID uuid) {
		return customerRepository.findOne(uuid);
	}
	
	
	public Customer createCustomer(PlainCustomer c) throws Exception {
//		if(c.getId()!=null)
//			throw new Exception("Creating a customer with an existing id is not permitted.");
		return customerRepository.save(new Customer(c.getFirstname(), c.getLastname(), c.getAddress()));
	}
	
	public Customer updateCustomer(PlainCustomer c) throws Exception {
		if(c.getId()==null)
			throw new Exception("Trying to update a customer without an ID. That's not possible!");
		return customerRepository.save(new Customer(c.getId(), c.getFirstname(), c.getLastname(), c.getAddress()));
	}
	
	public void deleteCustomer(UUID uuid) {
		customerRepository.delete(uuid);
	}
	
	
	
}
