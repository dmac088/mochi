package io.nzbee.entity.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.ports.IPromotionPortService;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.entity.promotion.IPromotionMapper;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.entity.promotion.PromotionDTO;
import io.nzbee.exceptions.promotion.PromotionNotFoundException;

@Component
public class PostgresPromotionAdapter implements IPromotionPortService {

	@Autowired
	private IPromotionService promotionService;
	
	@Autowired
	private IPromotionMapper promotionMapper;
	
	@Override
	public void save(Promotion domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Promotion domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Promotion domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Promotion findByCode(String locale, String code) {
		PromotionDTO dto = promotionService.findByCode(locale, code)
				.orElseThrow(() -> new PromotionNotFoundException("Promotion for code " + code + " not found!"));
		return promotionMapper.DTOToDo(dto);
	}

	@Override
	public Promotion findByCouponCode(String locale, String couponCode) {
		PromotionDTO dto = promotionService.findByCouponCode(locale, couponCode)
				.orElseThrow(() -> new PromotionNotFoundException("Promotion for coupon code " + couponCode + " not found!"));
		return promotionMapper.DTOToDo(dto);
	}

}
