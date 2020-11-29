package io.nzbee.entity.promotion;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
	@Cacheable(cacheNames = CACHE_NAME, key = "#promotionCode")
	public Optional<PromotionEntity> findByCode(String promotionCode) {
		return promotionRepository.findByPromotionCode(promotionCode);
	}

	@Override
	public Optional<PromotionEntity> findByDesc(String promotionDesc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<PromotionEntity> findAll() {
		return promotionRepository.findAll();
	}
	
	@Override
	@Caching(evict = {
			  @CacheEvict(cacheNames = CACHE_NAME, key="#promotion.promotionCode"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="{#promotion.locale, #promotion.promotionId}"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="{#promotion.locale, #promotion.promotionCode}"),
			  @CacheEvict(cacheNames = CACHE_NAME + "Other", allEntries = true)
			})
	public void save(PromotionEntity promotion) {
		promotionDao.save(promotion);
	}

	@Override
	public void update(PromotionEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PromotionEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PromotionDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionDTO> findDTOByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionEntity> findEntityByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionDTO> findByDesc(String locale, String desc) {
		return promotionDao.findByDesc(locale, desc);
	}

	@Override
	public Optional<PromotionEntity> findEntityByDesc(String locale, String desc) {
		return promotionRepository.findByAttributesLocaleAndAttributesPromotionDesc(locale, desc);
	}

}