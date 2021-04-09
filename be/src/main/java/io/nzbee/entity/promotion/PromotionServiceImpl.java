package io.nzbee.entity.promotion;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service(value="promotionEntityService")
public class PromotionServiceImpl implements IPromotionService {

	private static final String CACHE_NAME = "promotionCache";
	
	@Autowired
	private IPromotionRepository promotionRepository;
	
	@Autowired
	private IPromotionDao promotionDao;

	@Override
	public Optional<PromotionEntity> findById(Long promotionId) {
		return promotionRepository.findById(promotionId);
	}
	
	@Override
	public Optional<PromotionEntity> findByCode(String promotionCode) {
		return promotionRepository.findByPromotionCode(promotionCode);
	}
	@Override
	public Optional<PromotionEntity> findByDesc(String promotionDesc) {
		return promotionRepository.findByAttributesPromotionDesc(promotionDesc);
	}
	
	@Override
	public Optional<PromotionDTO> findByCode(String locale, String code) {
		return promotionDao.findByCode(locale, code);
	}
	
	@Override
	@Caching(evict = {
			  @CacheEvict(cacheNames = CACHE_NAME, key="#promotion.promotionCode"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#promotion.locale + \", \" + #promotion.promotionId"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#promotion.locale + \", \" + #promotion.promotionCode"),
			  @CacheEvict(cacheNames = CACHE_NAME + "Other", allEntries = true)
			})
	public void save(PromotionEntity promotion) {
		promotionDao.save(promotion);
	}

	@Override
	public void update(PromotionEntity t) {
		promotionDao.save(t);
	}

	@Override
	public void delete(PromotionEntity t) {
		promotionDao.delete(t);
	}

	

	

}