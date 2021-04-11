package io.nzbee.entity.brand.view;

import java.util.List;
import java.util.Optional;
import io.nzbee.domain.product.shipping.ShippingProduct;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.brand.view.BrandFacetViewDTO;
import io.nzbee.search.ISearchDimensionService;

public interface IBrandFacetService extends ISearchDimensionService<BrandFacetViewDTO> {
	
	List<BrandFacetViewDTO> findAll(String locale, String currency, String caetgoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper tagCodes, Double maxPrice);

	List<BrandFacetViewDTO> findAll(String locale, String rootCategory, String categoryCode);

	Optional<BrandFacetViewDTO> findByCode(String locale, String rootCategory, String code);

	Optional<BrandFacetViewDTO> findByDesc(String locale, String rootCategory, String desc);

	List<BrandFacetViewDTO> findAllByProductType(String locale, Class<?> cls);

	List<BrandFacetViewDTO> findByAllProductType(String locale, Class<ShippingProduct> class1);

}
