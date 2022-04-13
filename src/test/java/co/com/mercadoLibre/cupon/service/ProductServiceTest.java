package co.com.mercadoLibre.cupon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Map;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ProductServiceTest {

	private static ProductService productService;

	@Mock
	private static RestTemplate restTemplate;

	private static String url = "https://api.mercadolibre.com/items?ids=";

	private static JSONArray jsonArray;

	@Test
	public void getProductsPrices() {

		try {

			Mockito.when(restTemplate.getForEntity(anyString(), any()))
					.thenReturn(new ResponseEntity<>(jsonArray, HttpStatus.OK));

			productService = new ProductService(restTemplate);

			String ids = "1,2,3,4";

			Map<String, Float> response = productService.getProductPrices(ids);
			
			asser
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
