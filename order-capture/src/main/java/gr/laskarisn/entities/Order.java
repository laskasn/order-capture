package gr.laskarisn.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import gr.laskarisn.entities.customtypes.OrderStatus;
import gr.laskarisn.entities.customtypes.OrderStatusEnumType;;

@Entity(name="order")
@Table(name="\"order\"")
@TypeDef(name = "pgsql_enum", typeClass = OrderStatusEnumType.class)
//@JsonIdentityInfo(scope=Order.class, generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Order implements Serializable {


	private static final long serialVersionUID = -6103289303104325257L;


	@Id
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;
	
	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerid", referencedColumnName = "id")
	private Customer customer;
	
	@ManyToMany//(cascade = CascadeType.ALL)
	@JoinTable(
			name = "order_product", 
	        joinColumns={@JoinColumn(name="orderid", referencedColumnName="id")},
	        inverseJoinColumns={@JoinColumn(name="productid", referencedColumnName="id")}
	    )
	private List<Product> products = new ArrayList<>();

	
	@Column(name="orderdate")
	private Date orderdate;
	
	@Column(name="lastupdatedate")
	private Date lastupdatedate;
	
	@Enumerated(EnumType.STRING)
	@Column(name="orderstatus")
	@Type(type="pgsql_enum")
	private OrderStatus orderstatus;
	
	public Order() {
		this.orderstatus = OrderStatus.NEW;
	}
	
	public Order(UUID id) {
		this.id = id;
		this.orderstatus = OrderStatus.NEW;
	}
	
	
	public Order(Customer customer, List<Product> products, Date orderdate, Date lastupdatedate, OrderStatus orderstatus) {
		this.customer = customer;
		this.products = products;
		this.orderdate = orderdate;
		this.lastupdatedate = lastupdatedate;
		if(orderstatus==null)
			orderstatus = OrderStatus.NEW;
		this.orderstatus = orderstatus;
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProduct(List<Product> products) {
		this.products = products;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public Date getLastupdatedate() {
		return lastupdatedate;
	}
	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}
	public OrderStatus getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(OrderStatus orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	
	
	
}

