package co.com.mercadoLibre.cupon.threads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.mercadoLibre.cupon.service.restConsumer.ProductService;

public class InvoqueRestCallableTest {
	
	private static 	ProductService productService = Mockito.mock(ProductService.class);

	private static final float total = 100f;
	
	
	@Test
	public void callTest() {
		
		Map<String, Float> returnMock = new HashMap<>();
		returnMock.put("1", total);
		
		Mockito.when(productService.getProductPrices(anyString())).thenReturn(returnMock);
		
		InvoqueRestCallable invoqueRestCallable = new InvoqueRestCallable(productService,"");
		
		Map<String, Float> returnConsume= new HashMap<>();
		try {
			returnConsume = invoqueRestCallable.call();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		assertEquals(total, returnConsume.get("1"));
	}

}
