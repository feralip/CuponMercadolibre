package co.com.mercadoLibre.cupon.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Response implements Serializable{

	@JsonAlias("item_ids")
	private List<String> itemsIds;
	
	@JsonAlias("total")
	private Float total;
	
	public Response() {
		
	}

	public List<String> getItemsIds() {
		return itemsIds;
	}

	public void setItemsIds(List<String> itemsIds) {
		this.itemsIds = itemsIds;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
	
	
	
}
