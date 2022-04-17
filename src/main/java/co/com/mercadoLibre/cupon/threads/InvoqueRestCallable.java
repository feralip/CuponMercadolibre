package co.com.mercadoLibre.cupon.threads;

import java.util.Map;
import java.util.concurrent.Callable;

import co.com.mercadoLibre.cupon.service.restConsumer.ProductService;

public class InvoqueRestCallable implements Callable<Map<String, Float> > {
	
	ProductService productService;
	String productIds;
	
	public InvoqueRestCallable(ProductService productService, String productIds) {
		
		this.productService = productService;
		this.productIds = productIds;
	}
	
	public Map<String, Float> call() throws Exception{
		
		return productService.getProductPrices(productIds);
				
	}

}
