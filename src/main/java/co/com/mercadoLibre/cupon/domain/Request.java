package co.com.mercadoLibre.cupon.domain;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Request implements Serializable {
	
	@JsonProperty(value= "item_ids")
	private Set<String> itemIds;
	
	@JsonProperty(value= "amount")
	private Float amount;
	
	public Request() {
		
	}
		
	public Request(Float amount, Set<String> itemIds) {
		this.amount = amount;
		this.itemIds = itemIds;
	}

	public Set<String> getItemIds() {
		return itemIds;
	}

	public void setItemIds(Set<String> itemIds) {
		this.itemIds = itemIds;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	
	

}
