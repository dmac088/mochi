package io.nzbee.entity.promotion;

import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface IPromotionDao extends ILocalizedDao<PromotionDTO, PromotionEntity> {
	
	Set<PromotionDTO> findAll(String locale,  Set<String> PromotionCodes); 
	
	Optional<PromotionDTO> findByProductCode(String locale, String productCode);

	Set<PromotionDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

	Set<PromotionDTO> findAllByCategory(String locale, String categoryCode);

	Optional<PromotionEntity> findByCode(String code);
}
