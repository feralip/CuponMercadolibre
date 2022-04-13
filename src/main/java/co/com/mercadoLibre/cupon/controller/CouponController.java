package co.com.mercadoLibre.cupon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercadoLibre.cupon.domain.Response;
import co.com.mercadoLibre.cupon.domain.Request;

@RestController
@RequestMapping("/coupon/v1")
public class CouponController {
	
	@PostMapping(name = "/coupon/")
	public ResponseEntity<Response> validateCoupon(Request request){
		
		
		
		return null;
	}

}
