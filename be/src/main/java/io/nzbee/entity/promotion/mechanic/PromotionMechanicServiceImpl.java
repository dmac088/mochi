package io.nzbee.entity.promotion.mechanic;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="promotionMechanicEntityService")
public class PromotionMechanicServiceImpl implements IPromotionMechanicService {

	@Autowired
	private IPromotionMechanicRepository promotionMechanicRepository; 

	@Override
	public Optional<PromotionMechanicEntity> findById(Long promotionMechanicId) {
		return promotionMechanicRepository.findById(promotionMechanicId);
	}
	
	@Override
	public Optional<PromotionMechanicEntity> findByCode(String promotionMechanicCode) {
		return promotionMechanicRepository.findByPromotionMechanicCode(promotionMechanicCode);
	}

	@Override
	public Optional<PromotionMechanicEntity> findByDesc(String promotionMechanicDesc) {
		return promotionMechanicRepository.findByPromotionMechanicDesc(promotionMechanicDesc);
	}
	
	@Override
	public void save(PromotionMechanicEntity t) {
		promotionMechanicRepository.save(t);
	}

	@Override
	public void update(PromotionMechanicEntity t) {
		promotionMechanicRepository.save(t);
		
	}

	@Override
	public void delete(PromotionMechanicEntity t) {
		promotionMechanicRepository.delete(t);
		
	}

}