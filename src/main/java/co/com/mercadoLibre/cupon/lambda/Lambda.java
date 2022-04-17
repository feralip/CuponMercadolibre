package co.com.mercadoLibre.cupon.lambda;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import org.springframework.http.ResponseEntity;

import co.com.mercadoLibre.cupon.domain.Request;
import co.com.mercadoLibre.cupon.domain.Response;


public class Lambda extends SpringBootRequestHandler<Request, ResponseEntity<Response>>{

}
