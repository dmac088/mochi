package io.nzbee.entity.product.shipping;

import org.springframework.data.domain.Page;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.ProductDTO;

public interface IShippingProductService  {

	Page<ShippingProductDTO> findAll(String locale, String currency, String rootCategory,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort);
	
	ProductDTO findByDestinationAndTypeAndBagWeight(String locale, String shippingDestinationCode, String shippingTypeCode, Double bagWeightKg);
	
}
