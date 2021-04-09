package io.nzbee.entity.promotion;

import java.util.Optional;
import io.nzbee.entity.IDao;

public interface IPromotionDao extends IDao<PromotionEntity> {
	
	Optional<PromotionEntity> findByCode(String code);

	Optional<PromotionDTO> findByCode(String locale, String code);
}
