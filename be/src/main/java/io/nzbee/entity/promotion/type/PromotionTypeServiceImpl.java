package io.nzbee.entity.promotion.type;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service(value="promotionTypeEntityService")
public class PromotionTypeServiceImpl implements IPromotionTypeService {

	private static final String CACHE_NAME = "promotionTypeCache";
	
	@Autowired
	private IPromotionTypeRepository promotionTypeRepository; 

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#promotionTypeId")
	public Optional<PromotionType> findById(Long promotionTypeId) {
		return promotionTypeRepository.findById(promotionTypeId);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#promotionTypeCode")
	public Optional<PromotionType> findByCode(String promotionTypeCode) {
		return promotionTypeRepository.findByPromotionTypeCode(promotionTypeCode);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#promotionTypeDesc")
	public Optional<PromotionType> findByDesc(String promotionTypeDesc) {
		return promotionTypeRepository.findByPromotionTypeDesc(promotionTypeDesc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<PromotionType> findAll() {
		return promotionTypeRepository.findAll();
	}
	
	@Override
	public void save(PromotionType promotionType) {
		promotionTypeRepository.save(promotionType);
	}

	@Override
	public void update(PromotionType t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PromotionType t) {
		// TODO Auto-generated method stub
		
	}

}