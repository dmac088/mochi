package io.nzbee.entity.promotion.level;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="promotionLevelService")
public class PromotionLevelServiceImpl implements IPromotionLevelService {
	
	@Autowired
	private IPromotionLevelRepository promotionLevelRepository; 

	@Override
	public Optional<PromotionLevelEntity> findById(Long id) {
		return promotionLevelRepository.findById(id);
	}

	@Override
	public Optional<PromotionLevelEntity> findByCode(String code) {
		return promotionLevelRepository.findByPromotionLevelCode(code);
	}

	@Override
	public void save(PromotionLevelEntity t) {
		promotionLevelRepository.save(t);
	}

	@Override
	public void update(PromotionLevelEntity t) {
		promotionLevelRepository.save(t);		
	}

	@Override
	public void delete(PromotionLevelEntity t) {
		promotionLevelRepository.delete(t);
	}

}
