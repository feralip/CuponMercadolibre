package co.com.mercadoLibre.cupon.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RequestTest {
	
	private final Set<String> itemsIds = new HashSet();
	private final Float amount = 100.0F;
	
	
	@Test
	public void whitNoArgConstructor() {
		
		itemsIds.add("1");
		itemsIds.add("2");
		itemsIds.add("3");
		
		Request request = new Request();
		request.setAmount(amount);
		request.setItemIds(itemsIds);
				
		assertNotNull(request);
		assertEquals(request.getItemIds(), itemsIds);
		assertEquals(request.getAmount(), amount);
		
	}
	
	


}
