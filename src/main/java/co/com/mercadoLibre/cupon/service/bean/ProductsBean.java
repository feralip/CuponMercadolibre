package co.com.mercadoLibre.cupon.service.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.mercadoLibre.cupon.domain.Request;
import co.com.mercadoLibre.cupon.domain.Response;
import co.com.mercadoLibre.cupon.exceptions.ProductsNotFoundException;
import co.com.mercadoLibre.cupon.service.ICalculatorCuponProducts;
import co.com.mercadoLibre.cupon.service.restConsumer.ProductConsumer;
import co.com.mercadoLibre.cupon.threads.InvoqueRestExecutor;

@Service
public class ProductsBean {

	@Autowired
	ProductConsumer productConsumer;
	
	@Autowired
	ICalculatorCuponProducts calculatorCuponProducts;
	
	@Autowired
	InvoqueRestExecutor invoqueRestExecutor;
	
	public ProductsBean() {
		
	}
	
	public ProductsBean(ICalculatorCuponProducts calculatorCuponProducts, ProductConsumer productConsumer, InvoqueRestExecutor invoqueRestExecutor) {
		
		this.calculatorCuponProducts = calculatorCuponProducts;
		this.productConsumer = productConsumer;
		this.invoqueRestExecutor = invoqueRestExecutor;
	}

	public Response analyzeProcess(Request request) throws ProductsNotFoundException {

		if (request == null || request.getItemIds() == null || request.getItemIds().isEmpty()) {
			throw new ProductsNotFoundException("");
		}
		
		List<String> idSplited = splitIds(request.getItemIds(), 20);

		Map<String, Float> pricesById = invoqueRestExecutor.executeThread(productConsumer, idSplited);
		
		List<String> productsSelected = calculatorCuponProducts.calculate(pricesById, request.getAmount());
		
		
		if(productsSelected == null || productsSelected.isEmpty()) {
			
			throw new ProductsNotFoundException("");
		}
		
		
		AtomicReference<Float> total = new AtomicReference();
		total.set(0F);
		
		productsSelected.stream().forEach(itera -> total.set(total.get() + pricesById.get(itera)));
			
		Response response = new Response();
		response.setItemsIds(productsSelected);
		response.setTotal(total.get());
		return response;

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
				count = 0;
				returnIdList.add(temporalList.toString());
				temporalList = new StringBuilder();

			}
			count++;

		}
		
		returnIdList.add(temporalList.toString());
		

		return returnIdList;

	}
	
	

	
}
