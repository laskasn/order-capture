package gr.laskarisn.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name="product")
@Table(name="product")
//@JsonIdentityInfo(scope=Product.class, generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Product implements Serializable {


	private static final long serialVersionUID = 887830913094117368L;


	@Id
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;
	
	
	@Column(name="name")
	private String name;
	
	@Column(name="sku")
	private String sku;
	
	@Column(name="description")
	private String description;

	@OneToMany(mappedBy = "product", /*cascade = CascadeType.ALL , */ fetch = FetchType.LAZY)
	private Set<Order> orders;
	
	
	public Product() {
		
	}
	
	public Product(UUID id) {
		this.id = id;
	}
	
	public Product(String name, String sku, String description) {
		this.name = name;
		this.sku = sku;
		this.description = description;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	
	
	
	
	
	
	
	
}

