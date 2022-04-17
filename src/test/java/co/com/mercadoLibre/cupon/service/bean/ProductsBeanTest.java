package co.com.mercadoLibre.cupon.service.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import co.com.mercadoLibre.cupon.domain.Request;
import co.com.mercadoLibre.cupon.domain.Response;
import co.com.mercadoLibre.cupon.exceptions.ProductsNotFoundException;
import co.com.mercadoLibre.cupon.service.CalculatorCuponProductsImp;
import co.com.mercadoLibre.cupon.service.ICalculatorCuponProducts;
import co.com.mercadoLibre.cupon.service.restConsumer.ProductService;
import co.com.mercadoLibre.cupon.threads.InvoqueRestExecutor;

public class ProductsBeanTest {
	
	private static final float amount = 500F;
	
	
	private static ProductService productService = new ProductService();
		
	private static InvoqueRestExecutor invoqueRestExecutor  = Mockito.mock(InvoqueRestExecutor.class);
	
	private static ICalculatorCuponProducts calculatorCuponProducts = Mockito.mock(CalculatorCuponProductsImp.class);
	
	@Test
	public void analyzeProcessTest() {
		
		Map<String, Float> mapReturnMock = new HashMap();
		mapReturnMock.put("1", 450F);
		mapReturnMock.put("2", 150F);
		mapReturnMock.put("3", 50F);
		
		Mockito.when(invoqueRestExecutor.executeThread(any(), any()))
		.thenReturn(mapReturnMock);
		
		
		Set<String> itemIdsList = new HashSet<>();
		itemIdsList.add("1");
		itemIdsList.add("2");
		itemIdsList.add("3");
		
		Request request = new Request();
		request.setAmount(amount);
		request.setItemIds(itemIdsList);
		
		
		Response response = new Response();
		
		ProductsBean productBean = new ProductsBean(new CalculatorCuponProductsImp(), productService, invoqueRestExecutor); 
		
		try {
			
			response = productBean.analyzeProcess(request);
			
		}catch(ProductsNotFoundException e) {
			
			e.printStackTrace();
		}
		
		List<String> listItemsResponse = Arrays.asList("3","2");
		
		assertLinesMatch(listItemsResponse, response.getItemsIds(), "La lista response no coincide");
		assertEquals(200F, response.getTotal());
		
		
	}
	
	
	@Test
	public void analyzeProcessWithRequestEmptyTest() {
		
		
		Request request = new Request();
		request.setAmount(amount);
		request.setItemIds(null);
		
		
		Response response = null;
		
		ProductsBean productBean = new ProductsBean(new CalculatorCuponProductsImp(), productService, invoqueRestExecutor); 
		
		try {
			
			response = productBean.analyzeProcess(request);
			
		}catch(ProductsNotFoundException e) {
			
		}
		
		assertNull(response);
		
	}
	
	
	@Test
	public void analyzeProcessWithRestEmptyTest() {
		
		Mockito.when(calculatorCuponProducts.calculate(any(), Mockito.anyFloat())).thenReturn(null);
		
		Map<String, Float> mapReturnMock = new HashMap();
		mapReturnMock.put("1", 450F);
		mapReturnMock.put("2", 150F);
		mapReturnMock.put("3", 50F);
		mapReturnMock.put("4", 150F);
		mapReturnMock.put("5", 50F);
		mapReturnMock.put("6", 15F);
		mapReturnMock.put("7", 5F);
		mapReturnMock.put("8", 50F);
		mapReturnMock.put("9", 1F);
		mapReturnMock.put("10", 2F);
		mapReturnMock.put("11", 3F);
		mapReturnMock.put("12", 4F);
		mapReturnMock.put("13", 5F);
		mapReturnMock.put("14", 6F);
		mapReturnMock.put("15", 7F);
		mapReturnMock.put("16", 150F);
		mapReturnMock.put("17", 50F);
		mapReturnMock.put("18", 150F);
		mapReturnMock.put("19", 50F);
		mapReturnMock.put("20", 150F);
		mapReturnMock.put("21", 50F);
		
		Mockito.when(invoqueRestExecutor.executeThread(any(), any()))
		.thenReturn(mapReturnMock);
		
		
		Set<String> itemIdsList = new HashSet<>();
		itemIdsList.add("1");
		itemIdsList.add("2");
		itemIdsList.add("3");
		
		Request request = new Request();
		request.setAmount(amount);
		request.setItemIds(itemIdsList);
		
		
		Response response = null;
		
		ProductsBean productBean = new ProductsBean(calculatorCuponProducts, productService, invoqueRestExecutor); 
		
		try {
			
			response = productBean.analyzeProcess(request);
			
		}catch(ProductsNotFoundException e) {
			
		}
		
		assertNull(response);

		
	}

}
