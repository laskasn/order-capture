package gr.laskarisn.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import gr.laskarisn.app.Application;
import gr.laskarisn.entities.Customer;
import gr.laskarisn.entities.Order;
import gr.laskarisn.entities.Product;
import gr.laskarisn.entities.customtypes.OrderStatus;
import gr.laskarisn.repositories.CustomerRepository;
import gr.laskarisn.repositories.OrderRepository;
import gr.laskarisn.repositories.ProductRepository;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestAllControllers {

	
	public static Gson gson = new GsonBuilder()
	        .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()))
	        .registerTypeAdapter(Date.class, (JsonSerializer<Date>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.getTime()))
	        .create();

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    
    private static boolean initialized = false;


    private static List<Customer> customers = new ArrayList<Customer>();
    private static List<Product> products = new ArrayList<Product>();
    private static List<Order> orders = new ArrayList<Order>();
    

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    
    @Autowired
    private ProductRepository productRepository;
    

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setup() throws Exception {
    	
    	this.mockMvc = webAppContextSetup(webApplicationContext).build();

    	if(initialized)
    		return;
        
        this.orderRepository.deleteAllInBatch();
        this.customerRepository.deleteAllInBatch();
        this.productRepository.deleteAllInBatch();
        
        customers.add(this.customerRepository.save(new Customer("Nick", "Laskaris", "road 45, Athens, 12345")));
        customers.add(this.customerRepository.save(new Customer("Bill", "Laskaris", "uphill road 23, San Diego, California, 12345")));
        
        products.add(this.productRepository.save(new Product("product sample 1", "sku-452","sample product for testing")));
        products.add(this.productRepository.save(new Product("product sample 2", "sku-383","sample product for testing")));
        products.add(this.productRepository.save(new Product("product sample 3", "sku-2134","sample product for testing")));

        orders.add(this.orderRepository.save(new Order(customers.get(0), Arrays.asList(products.get(0), products.get(1)), new Date(), new Date(), OrderStatus.NEW)));
        orders.add(this.orderRepository.save(new Order(customers.get(1), Arrays.asList(products.get(2)), new Date(), new Date(), OrderStatus.NEW)));

      
        initialized = true;
    }

   
    @Test
    public void testCustomerByName() throws Exception {
    	  List<Customer> cus = this.customerRepository.findAnywhereInNames("askar");
          assertTrue(cus.size()==2);
    }
    
    
    @Test
    public void testCustomersRestCRUD() throws Exception {
    	
    	mockMvc.perform(get("/customer/count"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(contentType))
		   .andExpect(jsonPath("$", is(2)));
 	
    	Customer customer = new Customer("Jeff", "Bezos", "Dummy Street 36, Athens, Greece");
	 	
	 	String customerJson = mockMvc.perform(post("/customer/create").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(customer)))
		   .andExpect(status().isCreated())
		   .andReturn()
		   .getResponse().getContentAsString();
	 	
	 	customer = gson.fromJson(customerJson, Customer.class);
	 	
	 	mockMvc.perform(get("/customer/count"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(contentType))
		   .andExpect(jsonPath("$", is(3)));
	 	
	 	customer.setLastname("Bezosss");
	 	
	 	mockMvc.perform(put("/customer/update").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(customer)))
		   .andExpect(status().isCreated());
	 	
	 	mockMvc.perform(get("/customer/get/"+customer.getId().toString()))
	 	   .andExpect(status().isOk())
		   .andExpect(content().contentType(contentType))
		   .andExpect(jsonPath("$.lastname", is("Bezosss")));
	 	
 	
	 	mockMvc.perform(delete("/customer/delete/"+customer.getId().toString()))
	 		.andExpect(status().isOk());
	 	
	 	
	 	mockMvc.perform(get("/customer/count"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(contentType))
		   .andExpect(jsonPath("$", is(2)));
    }
    
    @Test
    public void testProductsRestCRUD() throws Exception {
    	
    	mockMvc.perform(get("/product/count"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(contentType))
		   .andExpect(jsonPath("$", is(3)));
    	
    	Product product = new Product("abc", "sku-65436", "Sample testing product");
    	
    	String productJson = mockMvc.perform(post("/product/create").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(product)))
			   .andExpect(status().isCreated())
			   .andReturn()
			   .getResponse().getContentAsString();
    	
    	product = gson.fromJson(productJson, Product.class);
    	
    	mockMvc.perform(get("/product/count"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(contentType))
		   .andExpect(jsonPath("$", is(4)));
    	
    	product.setDescription("SD");
    	
    	mockMvc.perform(put("/product/update").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(product)))
		   .andExpect(status().isCreated());
    	
    	mockMvc.perform(get("/product/get/"+product.getId().toString()))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(contentType))
		   .andExpect(jsonPath("$.description", is("SD")));
    	
    	
	 	mockMvc.perform(delete("/product/delete/"+product.getId().toString()))
 			.andExpect(status().isOk());
	 	
	 	
	 	mockMvc.perform(get("/product/count"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(contentType))
		   .andExpect(jsonPath("$", is(3)));
    	
    }
    
    @Test
    public void testOrdersRestCRUD() throws Exception  {
    	   	
    	mockMvc.perform(get("/order/count"))
    		   .andExpect(status().isOk())
    		   .andExpect(content().contentType(contentType))
    		   .andExpect(jsonPath("$", is(2)));
    	
    	assertTrue(!customers.isEmpty());
    	assertTrue(!products.isEmpty());
    	
    	Order order = new Order(customers.get(0), Arrays.asList(products.get(0)), new Date(), new Date(), OrderStatus.NEW);
    	
    	String orderJson = mockMvc.perform(post("/order/create").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(order)))
		   .andExpect(status().isCreated())
		   .andReturn()
		   .getResponse().getContentAsString();
    	
    	order = gson.fromJson(orderJson, Order.class);
    	
    	mockMvc.perform(get("/order/count"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(contentType))
		   .andExpect(jsonPath("$", is(3)));
    	
    	order.setOrderstatus(OrderStatus.COMPLETED);
    	
    	mockMvc.perform(put("/order/update").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(order)))
    			   .andExpect(status().isCreated());
    	
    	mockMvc.perform(get("/order/get/"+order.getId().toString()))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(contentType))
		   .andExpect(jsonPath("$.orderstatus", is("COMPLETED")));
    	
    	
    	mockMvc.perform(delete("/order/delete/"+order.getId().toString()))
    			.andExpect(status().isOk());
    	
    	
    	mockMvc.perform(get("/order/count"))
		   .andExpect(status().isOk())
		   .andExpect(content().contentType(contentType))
		   .andExpect(jsonPath("$", is(2)));
    	
    	
    	
    }
    
    
}