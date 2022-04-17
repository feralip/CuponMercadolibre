package co.com.mercadoLibre.cupon.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ResponseTest {
	
	private final List<String> itemsIds = Arrays.asList("1","2","1");
	private final Float total = 100.0F;
	
	
	@Test
	public void whitNoArgConstructor() {
		
		Response response = new Response();
		response.setItemsIds(itemsIds);
		response.setTotal(total);
		
		assertNotNull(response);
		assertEquals(response.getItemsIds(), itemsIds);
		assertEquals(response.getTotal(), total);
		
	}
	
	


}
