package io.nzbee.entity.promotion.mechanic;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service(value="promotionMechanicEntityService")
public class PromotionMechanicServiceImpl implements IPromotionMechanicService {

	private static final String CACHE_NAME = "promotionMechanicCache";
	
	@Autowired
	private IPromotionMechanicRepository promotionMechanicRepository; 

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#promotionMechanicId")
	public Optional<PromotionMechanic> findById(Long promotionMechanicId) {
		return promotionMechanicRepository.findById(promotionMechanicId);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#promotionMechanicCode")
	public Optional<PromotionMechanic> findByCode(String promotionMechanicCode) {
		return promotionMechanicRepository.findByPromotionMechanicCode(promotionMechanicCode);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#promotionMechanicDesc")
	public Optional<PromotionMechanic> findByDesc(String promotionMechanicDesc) {
		return promotionMechanicRepository.findByPromotionMechanicDesc(promotionMechanicDesc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<PromotionMechanic> findAll() {
		return promotionMechanicRepository.findAll();
	}
	
	@Override
	public void save(PromotionMechanic promotionMechanic) {
		promotionMechanicRepository.save(promotionMechanic);
	}

	@Override
	public void update(PromotionMechanic t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PromotionMechanic t) {
		// TODO Auto-generated method stub
		
	}

}