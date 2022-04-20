package co.com.mercadoLibre.cupon.service.restConsumer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONArray;

@Service
public class ProductConsumer {

	@Autowired
	private RestTemplate restTemplate;
	
	private final String url = "https://api.mercadolibre.com/items?ids=";

	
	public ProductConsumer() {
		
	}
	
	public ProductConsumer(RestTemplate restTemplate) {
		
		this.restTemplate = restTemplate;
	}

	public Map<String, Float> getProductPrices(String ids) {
		
		Map<String, Float> returnValues = new HashMap();
		
		ResponseEntity<JSONArray> response = restTemplate.getForEntity(url+ids,
				JSONArray.class);
		JSONArray products = response.getBody();
		
		products.stream().forEach(itera -> {
			
			LinkedHashMap<String, Object> product = (LinkedHashMap) itera; 
			if("200".equals(String.valueOf(product.get("code")))) {
				LinkedHashMap<String, Object> body = (LinkedHashMap)product.get("body");
				returnValues.put((String)body.get("id"), Float.valueOf(String.valueOf(body.get("price"))));
				
			}
		});
		
		return returnValues;
		
	}

}
