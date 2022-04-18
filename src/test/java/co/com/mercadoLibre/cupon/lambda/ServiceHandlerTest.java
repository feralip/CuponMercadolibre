package co.com.mercadoLibre.cupon.lambda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.mercadoLibre.cupon.domain.Request;
import co.com.mercadoLibre.cupon.domain.Response;
import co.com.mercadoLibre.cupon.exceptions.ProductsNotFoundException;
import co.com.mercadoLibre.cupon.function.CouponFunction;
import co.com.mercadoLibre.cupon.service.bean.ProductsBean;

public class ServiceHandlerTest {

	private static ProductsBean parallelProcess = Mockito.mock(ProductsBean.class);
	

	@Test
	public void testHandler200OK() throws ProductsNotFoundException {


		Set<String> listIds = new HashSet();
		listIds.add("1");
		listIds.add("2");
		listIds.add("3");

		Request request = new Request(500F, listIds);

		Mockito.when(parallelProcess.analyzeProcess(any(Request.class)))
				.thenReturn(new Response(250F, Arrays.asList("1", "2", "3")));

		CouponFunction serviceHandler = new CouponFunction(parallelProcess);
		ResponseEntity<Response> entity = serviceHandler.apply(request);

		assertEquals(HttpStatus.OK, entity.getStatusCode() );

	}
	
	@Test
	public void testHandler400NotFound() throws ProductsNotFoundException {


		Set<String> listIds = new HashSet();
		listIds.add("1");
		listIds.add("2");
		listIds.add("3");

		Request request = new Request(500F, listIds);

		Mockito.when(parallelProcess.analyzeProcess(any(Request.class)))
				.thenThrow(ProductsNotFoundException.class);

		CouponFunction serviceHandler = new CouponFunction(parallelProcess);
		ResponseEntity<Response> entity = serviceHandler.apply(request);

		assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode() );

	}

}
