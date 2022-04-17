package co.com.mercadoLibre.cupon.domain;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Request implements Serializable {
	
	@JsonAlias("item_ids")
	Set<String> itemIds;
	
	@JsonAlias("amount")
	Float amount;
	
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
