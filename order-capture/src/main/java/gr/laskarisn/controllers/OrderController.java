package gr.laskarisn.controllers;

import org.springframework.web.bind.annotation.RestController;

import gr.laskarisn.entities.Order;
import gr.laskarisn.entities.customtypes.OrderStatus;
import gr.laskarisn.messengers.OrderProducts;
import gr.laskarisn.repositories.OrderRepository;
import gr.laskarisn.services.OrderService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
	
	
	private OrderService orderService;
	
	@Autowired
	public void setOrderRepository(OrderService orderService) {
		this.orderService = orderService;
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = { "/count" })
	@ResponseBody
    public Long count() {
		return orderService.countAllOrders();
    }
	
	
	@RequestMapping(method = RequestMethod.GET, value = { "/list" })
	@ResponseBody
    public List<Order> list() {
		return orderService.listAllOrders();
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
		Order order = orderService.findOrder(uuid);
		return ResponseEntity.status(HttpStatus.OK).body(order);
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = { "/create" }, consumes = "application/json", produces="application/json")
	public @ResponseBody ResponseEntity<Object> create(@RequestBody OrderProducts neworder) {
		Order order = new Order();
		try{
			order = orderService.createOrder(neworder);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not create!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = { "/updatestatus" }, consumes = "application/json", produces="application/json")
	public @ResponseBody ResponseEntity<Object> updatestatus(@RequestParam("orderid") UUID id, @RequestParam("status") OrderStatus status) {
		Order order = new Order();
		try{
			order = orderService.updateOrderStatus(id, status);
		}
		catch(Exception ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not update!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
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
			orderService.deleteOrder(uuid);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully!");
	}
	
	
    
    
}
