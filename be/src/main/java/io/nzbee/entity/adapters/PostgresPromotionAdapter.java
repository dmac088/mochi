package io.nzbee.entity.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.ports.IPromotionPortService;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.order.OrderPromotion;
import io.nzbee.entity.promotion.IPromotionMapper;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.entity.promotion.PromotionDTO;
import io.nzbee.entity.promotion.order.IPromotionOrderService;
import io.nzbee.entity.promotion.order.PromotionOrderDTO;
import io.nzbee.exceptions.NotFoundException;

@Component
public class PostgresPromotionAdapter implements IPromotionPortService {

	@Autowired
	private IPromotionService promotionService;
	
	@Autowired
	private IPromotionOrderService promotionOrderService;
	
	@Autowired
	private IPromotionMapper promotionMapper;
	
	@Override
	public void save(Promotion domainObject) {
		promotionService.save(promotionMapper.doToEntity(domainObject));
	}

	@Override
	public void update(Promotion domainObject) {
		promotionService.update(promotionMapper.doToEntity(domainObject));
	}

	@Override
	public void delete(Promotion domainObject) {
		promotionService.delete(promotionMapper.doToEntity(domainObject));
	}

	@Override
	public Promotion findByCode(String locale, String code) {
		PromotionDTO dto = promotionService.findByCode(locale, code)
				.orElseThrow(() -> new NotFoundException("Promotion for code " + code + " not found!"));
		return promotionMapper.DTOToDo(dto);
	}
	
	@Override
	public OrderPromotion findOrderPromotionByCode(String locale, String code) {
		PromotionOrderDTO dto = promotionOrderService.findByCode(locale, code)
				.orElseThrow(() -> new NotFoundException("Promotion for code " + code + " not found!"));
		return (OrderPromotion) promotionMapper.DTOToDo(dto);
	}

	@Override
	public OrderPromotion findOrderPromotionByCouponCode(String locale, String couponCode) {
		PromotionOrderDTO dto = promotionOrderService.findByCouponCode(locale, couponCode)
				.orElseThrow(() -> new NotFoundException("Promotion for coupon code " + couponCode + " not found!"));
		return (OrderPromotion) promotionMapper.DTOToDo(dto);
	}

	

}
