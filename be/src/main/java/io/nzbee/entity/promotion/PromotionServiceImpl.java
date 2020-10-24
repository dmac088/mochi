package io.nzbee.entity.promotion;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service(value="promotionEntityService")
public class PromotionServiceImpl implements IPromotionService {

	private static final String CACHE_NAME = "promotionCache";
	
	@Autowired
	private IPromotionRepository promotionRepository; 

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#promotionId")
	public Optional<Promotion> findById(Long promotionId) {
		return promotionRepository.findById(promotionId);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#promotionCode")
	public Optional<Promotion> findByCode(String promotionCode) {
		return promotionRepository.findByPromotionCode(promotionCode);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#promotionDesc")
	public Optional<Promotion> findByDesc(String promotionDesc) {
		return promotionRepository.findByPromotionShortDesc(promotionDesc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Promotion> findAll() {
		return promotionRepository.findAll();
	}
	
	@Override
	public void save(Promotion promotion) {
		promotionRepository.save(promotion);
	}

	@Override
	public void update(Promotion t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Promotion t) {
		// TODO Auto-generated method stub
		
	}

}