package co.com.mercadoLibre.cupon.service;

import java.util.List;
import java.util.Map;

public interface ICalculatorCuponProducts {
	
	public List<String> calculate(Map<String, Float> items, Float amount);

}
