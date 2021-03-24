package io.nzbee.entity.product.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.IProductDao;
import io.nzbee.entity.product.ProductDTO;

@Service
public class ShippingProductServiceImpl implements IShippingProductService {
	
	@Autowired
	private IProductDao productDAO;

	@Override
	public Page<ShippingProductDTO> findAll(String locale, String currency, String rootCategory,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort) {
		return productDAO.findAll(
				  locale,
		 		  currency,
		 		  rootCategory,
		 		  categoryCodes,
		 		  brandCodes, 
		 		  tagCodes,
		 		  maxPrice,
		 		  ShippingProductEntity.class,
		 		  page,
		 		  size,
		 		  sort
		  	).map(p -> (ShippingProductDTO) p);
	}

	@Override
	public ProductDTO findByDestinationAndTypeAndBagWeight(String locale, String shippingDestinationCode,
			String shippingTypeCode, Double bagWeightKg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
