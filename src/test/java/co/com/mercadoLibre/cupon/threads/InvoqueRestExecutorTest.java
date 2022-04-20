package co.com.mercadoLibre.cupon.threads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.mercadoLibre.cupon.service.restConsumer.ProductConsumer;

public class InvoqueRestExecutorTest {
	
	private static 	ProductConsumer productConsumer = Mockito.mock(ProductConsumer.class);

	private static final float total = 100f;

	
	@Test
	public void executeThread() {
		
		Map<String, Float> returnMock = new HashMap<>();
		returnMock.put("1", total);
		
		Mockito.when(productConsumer.getProductPrices(anyString())).thenReturn(returnMock);
		
		List<String> idsList = new ArrayList();
		idsList.add("1,2,3,4");
		idsList.add("5,6,7,8");
		
		InvoqueRestExecutor invoqueRestExecutor = new InvoqueRestExecutor();
		Map<String, Float> returnMap = invoqueRestExecutor.executeThread(productConsumer, idsList);
	
		assertEquals(total, returnMap.get("1"));
	}

}
