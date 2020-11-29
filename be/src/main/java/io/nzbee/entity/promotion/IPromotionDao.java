package io.nzbee.entity.promotion;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface IPromotionDao extends ILocalizedDao<PromotionDTO, PromotionEntity> {
	
	List<PromotionDTO> findAll(String locale,  Set<String> PromotionCodes); 
	
	Optional<PromotionDTO> findByProductCode(String locale, String productCode);

	List<PromotionDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

	List<PromotionDTO> findAllByCategory(String locale, String categoryCode);

	Optional<PromotionEntity> findByCode(String code);
}
