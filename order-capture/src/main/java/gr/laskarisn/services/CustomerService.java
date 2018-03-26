package gr.laskarisn.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.laskarisn.entities.Customer;
import gr.laskarisn.entities.Product;
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
	
	
	public Customer createCustomer(Customer customer) throws Exception {
		if(customer.getId()!=null)
			throw new Exception("Creating a customer with an existing id is not permitted.");
		return customerRepository.save(customer);
	}
	
	public Customer updateCustomer(Customer customer) throws Exception {
		if(customer.getId()==null)
			throw new Exception("Trying to update a customer without an ID. That's not allowed!");
		return customerRepository.save(customer);
	}
	
	public void deleteCustomer(UUID uuid) {
		customerRepository.delete(uuid);
	}
	
	
	
}
