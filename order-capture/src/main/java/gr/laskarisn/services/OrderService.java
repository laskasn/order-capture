package gr.laskarisn.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.laskarisn.entities.Customer;
import gr.laskarisn.entities.Order;
import gr.laskarisn.entities.Product;
import gr.laskarisn.entities.customtypes.OrderStatus;
import gr.laskarisn.messengers.OrderProducts;
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
	
	public Order createOrder(OrderProducts neworder) throws Exception {
		
		Order order = new Order();
		order.setCustomer(new Customer(neworder.getCustomerID()));
		order.setProduct(neworder.getProductIDs().parallelStream().map(id -> new Product(id)).collect(Collectors.toList()));
		order.setOrderdate(new Date());
		order.setLastupdatedate(new Date());
		return orderRepository.save(order);
	}
	
	public Order updateOrderStatus(UUID orderid, OrderStatus orderstatus) {
		Order order = orderRepository.getOne(orderid);
		order.setOrderstatus(orderstatus);
		return orderRepository.save(order);
	}
	
//	public Order updateOrder(Order order) throws Exception {
//		order.setLastupdatedate(new Date());
//		return orderRepository.save(order);
//	}
	
	
	public void deleteOrder(UUID uuid) {
		orderRepository.delete(uuid);
	}
	
	
	
	
}
