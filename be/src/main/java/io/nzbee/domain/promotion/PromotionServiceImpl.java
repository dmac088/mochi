package io.nzbee.domain.promotion;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.ports.IPromotionPortService;

public class PromotionServiceImpl implements IPromotionService {

	@Autowired
	private IPromotionPortService promotionService;
	
	@Override
	public Promotion findByCode(String locale, String code) {
		return promotionService.findByCode(locale, code);
	}
	
	@Override
	public Promotion findByCouponCode(String locale, String couponCode) {
		return promotionService.findByCouponCode(locale, couponCode);
	}

	@Override
	public Promotion findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promotion> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promotion> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Promotion object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Promotion object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Promotion object) {
		// TODO Auto-generated method stub
		
	}

}
