package co.com.mercadoLibre.cupon.threads;

import java.util.Map;
import java.util.concurrent.Callable;

import co.com.mercadoLibre.cupon.service.restConsumer.ProductConsumer;

public class InvoqueRestCallable implements Callable<Map<String, Float> > {
	
	ProductConsumer productConsumer;
	String productIds;
	
	public InvoqueRestCallable(ProductConsumer productConsumer, String productIds) {
		
		this.productConsumer = productConsumer;
		this.productIds = productIds;
	}
	
	public Map<String, Float> call() throws Exception{
		
		return productConsumer.getProductPrices(productIds);
				
	}

}
