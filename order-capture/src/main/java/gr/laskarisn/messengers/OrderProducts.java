package gr.laskarisn.messengers;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class OrderProducts implements Serializable {

	
	private static final long serialVersionUID = 9061169743214031350L;
	
	
	private UUID customerID;
	private List<UUID> productIDs;
	
	public UUID getCustomerID() {
		return customerID;
	}
	public void setCustomerID(UUID customerID) {
		this.customerID = customerID;
	}
	public List<UUID> getProductIDs() {
		return productIDs;
	}
	public void setProductIDs(List<UUID> productIDs) {
		this.productIDs = productIDs;
	}
	
	
	
}
