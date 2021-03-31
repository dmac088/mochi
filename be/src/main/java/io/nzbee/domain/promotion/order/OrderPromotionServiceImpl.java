package io.nzbee.domain.promotion.order;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.ports.IPromotionPortService;

public class OrderPromotionServiceImpl implements IOrderPromotionService {

	@Autowired
	private IPromotionPortService promotionService;
	
	@Override
	public OrderPromotion findByCode(String locale, String code) {
		return promotionService.findOrderPromotionByCode(locale, code);
	}
	
	@Override
	public OrderPromotion findByCouponCode(String locale, String couponCode) {
		return promotionService.findOrderPromotionByCouponCode(locale, couponCode);
	}

	@Override
	public OrderPromotion findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderPromotion> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderPromotion> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(OrderPromotion object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OrderPromotion object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(OrderPromotion object) {
		// TODO Auto-generated method stub
		
	}

}
