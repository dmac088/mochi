package io.nzbee.entity.promotion.order;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="promotionOrderService")
public class PromotionOrderServiceImpl implements IPromotionOrderService {

	@Autowired
	private IPromotionOrderRepository promotionOrderRepository;
	
	@Override
	public List<PromotionOrderEntity> findAll() {
		return promotionOrderRepository.findAll();
	}

	@Override
	public Optional<PromotionOrderEntity> findById(Long id) {
		return promotionOrderRepository.findById(id);
	}

	@Override
	public Optional<PromotionOrderEntity> findByCode(String code) {
		return promotionOrderRepository.findByPromotionCode(code);
	}
	
	@Override
	public Optional<PromotionOrderDTO> findByCode(String locale, String code) {
		return promotionOrderRepository.findByCode(locale, code);
	}

	@Override
	public Optional<PromotionOrderDTO> findByCouponCode(String locale, String couponCode) {
		return promotionOrderRepository.findByPromotionCouponCode(locale, couponCode);
	}

	@Override
	public void save(PromotionOrderEntity t) {
		promotionOrderRepository.save(t);
	}

	@Override
	public void update(PromotionOrderEntity t) {
		promotionOrderRepository.save(t);
	}

	@Override
	public void delete(PromotionOrderEntity t) {
		promotionOrderRepository.delete(t);
		
	}

}
