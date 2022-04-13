package co.com.mercadoLibre.cupon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.mercadoLibre.cupon.domain.Request;
import co.com.mercadoLibre.cupon.threads.InvoqueRestCallable;

@Service
public class ProcesarProceso {

	@Autowired
	ProductService productService;
	
	@Autowired
	ICalculatorCuponProducts calculatorCuponProducts;

	public void analyzeProcess(Request request) {

		if (request.getItemIds() != null && request.getItemIds().isEmpty()) {
			return;
		}
		
		List<String> idSplited = splitIds(request.getItemIds(), 20);

		Map<String, Float> pricesById = executeThread(idSplited);
		
		List<String> productsSelected = calculatorCuponProducts.calculate(pricesById, request.getAmount());
		

	}

	private List<String> splitIds(Set<String> ids, int size) {

		List<String> returnIdList = new ArrayList<>();
		int count = 1;

		StringBuilder temporalList = new StringBuilder();
		for (String itera : ids) {

			if (count == 1) {
				temporalList.append(itera);
			} else {

				temporalList.append(",").append(itera);
			}

			if (count >= size) {
				count = 1;
				returnIdList.add(temporalList.toString());
				temporalList = new StringBuilder();

			}

		}
		
		
		

		return returnIdList;

	}
	
	private Map<String, Float> executeThread(List<String> idSplited) {
		
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
