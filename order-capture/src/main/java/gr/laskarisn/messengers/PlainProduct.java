package gr.laskarisn.messengers;

import java.io.Serializable;
import java.util.UUID;

public class PlainProduct implements Serializable{

	private static final long serialVersionUID = 4608855832525823303L;

	
	private UUID id;
	private String name;
	private String sku;
	private String description;
	
	
	public PlainProduct() {
	}

	public PlainProduct(UUID id) {
		this.id = id;
	}
	
	public PlainProduct(String name, String sku, String description) {
		this.name = name;
		this.sku = sku;
		this.description = description;
	}
	
	public PlainProduct(String name, String sku, String description, UUID id) {
		this.id = id;
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
	
	
	
	
	
	
}
