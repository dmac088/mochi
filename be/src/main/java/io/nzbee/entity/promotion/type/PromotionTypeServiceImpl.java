package io.nzbee.entity.promotion.type;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class PromotionTypeServiceImpl implements IPromotionTypeService {

	@Autowired 
	private IPromotionTypeRepository promotionTypeRepository;
	
	@Override
	public List<PromotionTypeEntity> findAll() {
		return promotionTypeRepository.findAll();
	}

	@Override
	public Optional<PromotionTypeEntity> findById(Long id) {
		return promotionTypeRepository.findById(id);
	}

	@Override
	public Optional<PromotionTypeEntity> findByCode(String code) {
		return promotionTypeRepository.findByPromotionTypeCode(code);
	}

	@Override
	public void save(PromotionTypeEntity t) {
		promotionTypeRepository.save(t);
	}

	@Override
	public void update(PromotionTypeEntity t) {
		promotionTypeRepository.save(t);
	}

	@Override
	public void delete(PromotionTypeEntity t) {
		promotionTypeRepository.delete(t);
		
	}

}
