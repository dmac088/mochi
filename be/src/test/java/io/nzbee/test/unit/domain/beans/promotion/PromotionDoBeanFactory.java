package io.nzbee.test.unit.domain.beans.promotion;

import java.time.LocalDateTime;
import java.time.Month;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.product.ProductPromotion;

@Service
@Profile(value = "ut")
public class PromotionDoBeanFactory implements IPromotionDoBeanFactory {

	@Override
	public final Promotion getBean() {
		
		return new ProductPromotion(	 "TST01", 
										 "Test Promotion 1",
										 LocalDateTime.of(2015, 
		                                         Month.JULY, 29, 19, 30, 40),
										 LocalDateTime.of(2019, 
		                                         Month.JULY, 29, 19, 30, 40),
										 "TST01",
										 "Test Mechanic 1",
										 "TST01",
										 "Test Promotion Type 1");
		
	}
	
}
