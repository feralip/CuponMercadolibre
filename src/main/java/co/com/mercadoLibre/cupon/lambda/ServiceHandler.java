package co.com.mercadoLibre.cupon.lambda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import co.com.mercadoLibre.cupon.domain.Request;
import co.com.mercadoLibre.cupon.domain.Response;
import co.com.mercadoLibre.cupon.exceptions.ProductsNotFoundException;
import co.com.mercadoLibre.cupon.service.bean.ProductsBean;

public class ServiceHandler implements RequestHandler<Request, ResponseEntity<Response>> {

	@Autowired
	private ProductsBean parallelProcess;
	
	public ServiceHandler(ProductsBean parallelProcess) {
		this.parallelProcess = parallelProcess;
	}

	@Override
	public ResponseEntity<Response> handleRequest(Request request, Context context) {

		Response response = null;
		try {
			response = parallelProcess.analyzeProcess(request);
		} catch (ProductsNotFoundException e) {

			return new ResponseEntity(HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
}
