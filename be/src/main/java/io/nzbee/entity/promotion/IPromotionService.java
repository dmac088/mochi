package io.nzbee.entity.promotion;

import java.util.Optional;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.search.ISearchDimensionService;

public interface IPromotionService extends ILocalizedService<PromotionDTO, PromotionEntity>, ISearchDimensionService<PromotionDTO> {

	Optional<PromotionEntity> findByDesc(String promotionMechanicDesc);
	
}
