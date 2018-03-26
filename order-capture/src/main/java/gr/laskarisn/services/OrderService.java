package gr.laskarisn.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.laskarisn.entities.Order;
import gr.laskarisn.repositories.OrderRepository;

@Service
public class OrderService {

	
	@Autowired
	OrderRepository orderRepository;
	
	
	
	public List<Order> listAllOrders() {
		List<Order> orders = orderRepository.findAll();
		orders.parallelStream().forEach(order -> {
			order.getProducts().size(); //trigger lazy evaluation, just for this object
			order.getCustomer();
			}
		);
		return orders;
	}
	
	
	public Long countAllOrders() {
		return orderRepository.count();
	}
	
	
	public Order findOrder(UUID uuid) {
		return orderRepository.findOne(uuid);
	}
	
	public Order createOrder(Order order) throws Exception {
		order.setOrderdate(new Date());
		order.setLastupdatedate(new Date());
		return orderRepository.save(order);
	}
	
	public Order updateOrder(Order order) throws Exception {
		order.setLastupdatedate(new Date());
		return orderRepository.save(order);
	}
	
	
	public void deleteOrder(UUID uuid) {
		orderRepository.delete(uuid);
	}
	
	
	
	
}
