package io.nzbee.entity.promotion;

import java.util.Optional;
import io.nzbee.entity.ILightLocalizedService;
import io.nzbee.search.ISearchDimensionService;

public interface IPromotionService extends ILightLocalizedService<PromotionDTO, PromotionEntity>, ISearchDimensionService<PromotionDTO> {

	Optional<PromotionEntity> findByDesc(String promotionDesc);

	Optional<PromotionEntity> findByCode(String promotionCode);

	Optional<PromotionEntity> findById(Long promotionId);
	
}
