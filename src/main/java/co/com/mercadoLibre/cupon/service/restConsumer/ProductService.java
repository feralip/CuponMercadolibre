package co.com.mercadoLibre.cupon.service.restConsumer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class ProductService {

	@Autowired
	private RestTemplate restTemplate;
	
	private final String url = "https://api.mercadolibre.com/items?ids=";

	
	public ProductService() {
		
	}
	
	public ProductService(RestTemplate restTemplate) {
		
		this.restTemplate = restTemplate;
	}

	public Map<String, Float> getProductPrices(String ids) {
		
		Map<String, Float> returnValues = new HashMap();
		
		ResponseEntity<JSONArray> response = restTemplate.getForEntity(url+ids,
				JSONArray.class);
		JSONArray products = response.getBody();
		
		products.stream().forEach(itera -> {
			
			JSONObject product = new JSONObject((JSONObject)itera); 
			if("200".equals(product.get("code"))) {
				returnValues.put((String)((JSONObject)product.get("body")).get("id"), (Float)((JSONObject)product.get("body")).get("price"));
				
			}
		});
		
		return returnValues;
		
	}

}
