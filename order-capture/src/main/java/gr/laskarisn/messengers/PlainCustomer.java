package gr.laskarisn.messengers;

import java.io.Serializable;
import java.util.UUID;

public class PlainCustomer implements Serializable{

	private static final long serialVersionUID = -2672614245766609732L;

	private UUID id;
	private String firstname;
	private String lastname;
	private String address;
	
	public PlainCustomer() {
		
	}
	
	public PlainCustomer(String firstname, String lastname, String address) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}

	public PlainCustomer(String firstname, String lastname, String address, UUID id) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	
}


