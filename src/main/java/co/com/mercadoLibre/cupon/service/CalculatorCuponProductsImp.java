package co.com.mercadoLibre.cupon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Service;

@Service
public class CalculatorCuponProductsImp implements ICalculatorCuponProducts {

	public List<String> calculate(Map<String, Float> items, Float amount) {
		
		List<String> itemsSelected = new ArrayList();
		AtomicReference<Float> total = new AtomicReference();
		total.set(0F);
				
		items.entrySet().stream().sorted((v1, v2) -> v1.getValue().compareTo(v2.getValue())).forEachOrdered(item -> {
			
			total.set(total.get() + item.getValue() ) ;
			
			if(total.get() < amount ) {
				
				itemsSelected.add(item.getKey());
			
			}else {
				return;
			}
			
		});
		
		return itemsSelected;
			
		
	}

}
