package co.com.mercadoLibre.cupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercadoLibre.cupon.domain.Request;
import co.com.mercadoLibre.cupon.domain.Response;
import co.com.mercadoLibre.cupon.exceptions.ProductsNotFoundException;
import co.com.mercadoLibre.cupon.service.bean.ProductsBean;

@RestController
@RequestMapping("/coupon")
public class CouponController {
	
	@Autowired
	private ProductsBean parallelProcess;
	
	public CouponController(ProductsBean parallelProcess) {
		this.parallelProcess = parallelProcess;
	}
	
	@PostMapping()
	public ResponseEntity<Response> validateCoupon(@RequestBody  Request request){
		
		
		Response response = null;
		try {
			response = parallelProcess.analyzeProcess(request);
		} catch (ProductsNotFoundException e) {

			return new ResponseEntity(HttpStatus.NOT_FOUND);

		}

		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
