package io.nzbee.entity.promotion.mechanic;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="promotionMechanicEntityService")
public class PromotionMechanicServiceImpl implements IPromotionMechanicService {

	private static final String CACHE_NAME = "promotionMechanicCache";
	
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
	public List<PromotionMechanicEntity> findAll() {
		return promotionMechanicRepository.findAll();
	}
	
	@Override
	public void save(PromotionMechanicEntity promotionMechanic) {
		promotionMechanicRepository.save(promotionMechanic);
	}

	@Override
	public void update(PromotionMechanicEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PromotionMechanicEntity t) {
		// TODO Auto-generated method stub
		
	}

}