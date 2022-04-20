package co.com.mercadoLibre.cupon.service.restConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ProductConsumerTest {


	private static RestTemplate restTemplate = Mockito.mock(RestTemplate.class);


	private static JSONArray jsonArray;

	@Test
	public void getProductsPricesTest() {
		
		

		try {
			
			JSONObject objectOne = new JSONObject();
			objectOne.put("id", "1");
			objectOne.put("title", "Dato de prueba 1");
			objectOne.put("price", 180F);
			
			JSONObject objectTwo = new JSONObject();
			objectTwo.put("id", "2");
			objectTwo.put("title", "Dato de prueba 2");
			objectTwo.put("price", 200F);
			
			jsonArray.put(objectTwo);
			jsonArray.put(objectOne);
			
			Mockito.when(restTemplate.getForEntity(anyString(), any()))
			.thenReturn(new ResponseEntity<>(jsonArray, HttpStatus.OK));

			ProductConsumer productConsumer = new ProductConsumer(restTemplate);

			String ids = "1,2,3,4";

			Map<String, Float> response = productConsumer.getProductPrices(ids);
			assertEquals(response.get("1"), 180F);
			assertEquals(response.get("2"), 200F);
			
			
		} catch (Exception e) {
			
		}

	}

}
