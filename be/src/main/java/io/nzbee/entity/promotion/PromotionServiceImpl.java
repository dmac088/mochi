package io.nzbee.entity.promotion;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="promotionEntityService")
public class PromotionServiceImpl implements IPromotionService {

	
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<PromotionEntity> findAll() {
		return promotionRepository.findAll();
	}
	
	@Override
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
	public Set<PromotionDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PromotionDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PromotionEntity> findAll(Set<String> codes) {
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