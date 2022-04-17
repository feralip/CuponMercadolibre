package co.com.mercadoLibre.cupon.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import co.com.mercadoLibre.cupon.service.restConsumer.ProductService;

@Service
public class InvoqueRestExecutor {
	
public Map<String, Float> executeThread(ProductService productService, List<String> idSplited) {
		
		Map<String, Float> pricesById = new HashMap<>();
				
				
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<Map<String, Float>>> list = new ArrayList<Future<Map<String, Float>>>();
				
		for (String iteraId: idSplited) {
			
			Callable<Map<String,Float>> callable = new InvoqueRestCallable(productService, iteraId);
			Future<Map<String,Float>> future = executor.submit(callable);
			list.add(future);
		}
		
		
		for (Future<Map<String,Float>> iteraFuture : list) {
			try {
				
				pricesById.putAll(iteraFuture.get());
				
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		executor.shutdown();
		
		return pricesById;
	}

}
