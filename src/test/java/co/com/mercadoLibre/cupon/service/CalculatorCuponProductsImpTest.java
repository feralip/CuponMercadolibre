package co.com.mercadoLibre.cupon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
public class CalculatorCuponProductsImpTest {

	@Test
	public void calculatorTest() {
		
		Map<String, Float> items = new HashMap();
		items.put("1", 100F);
		items.put("2", 210F);
		items.put("3", 260F);
		items.put("4", 80F);
		items.put("5", 90F);
		
		Float value = 500F;
		
		ICalculatorCuponProducts calculator = new CalculatorCuponProductsImp();
		List<String> resultObtained = calculator.calculate(items, value);		
		List<String> resultExpected = Arrays.asList("4", "5", "1", "2");
		
		
		Assertions.assertLinesMatch(resultObtained, resultExpected); 

	}
	
}
