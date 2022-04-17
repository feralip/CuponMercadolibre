package co.com.mercadoLibre.cupon.lambda;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.com.mercadoLibre.cupon.domain.Request;
import co.com.mercadoLibre.cupon.domain.Response;
import co.com.mercadoLibre.cupon.exceptions.ProductsNotFoundException;
import co.com.mercadoLibre.cupon.service.bean.ProductsBean;

@Component("ServiceHandler")
public class ServiceHandler implements Function<Request, ResponseEntity<Response>> {

	@Autowired
	private ProductsBean parallelProcess;
	
	public ServiceHandler(ProductsBean parallelProcess) {
		this.parallelProcess = parallelProcess;
	}

	@Override
	public ResponseEntity<Response> apply(Request request) {

		Response response = null;
		try {
			response = parallelProcess.analyzeProcess(request);
		} catch (ProductsNotFoundException e) {

			return new ResponseEntity(HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
}
