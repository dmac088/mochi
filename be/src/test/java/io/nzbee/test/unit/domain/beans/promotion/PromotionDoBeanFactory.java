package io.nzbee.test.unit.domain.beans.promotion;

import java.time.LocalDateTime;
import java.time.Month;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.PromotionType;

public class PromotionDoBeanFactory implements IPromotionDoBeanFactory {

	@Override
	public final Promotion getBean() {
		
		return new Promotion(	 "TST01", 
								 "Test Promotion 1",
								 LocalDateTime.of(2015, 
                                         Month.JULY, 29, 19, 30, 40),
								 LocalDateTime.of(2019, 
                                         Month.JULY, 29, 19, 30, 40),
								 this.getPromotionType());
		
	}
	
	
	public final PromotionType getPromotionType() {
		return new PromotionType(
					"TST01",
					"Test Promotion Type 1"
					);
	}
	
}
