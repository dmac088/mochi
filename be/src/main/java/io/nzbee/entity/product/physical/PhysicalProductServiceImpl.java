package io.nzbee.entity.product.physical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.IProductDao;

@Service
public class PhysicalProductServiceImpl implements IPhysicalProductService {

	@Autowired
	private IProductDao productDAO;

	@Override
	public Page<PhysicalProductDTO> findAll(String locale, String currency, String rootCategory,
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
		 		  PhysicalProductEntity.class,
		 		  page,
		 		  size,
		 		  sort
		  	).map(p -> (PhysicalProductDTO) p);
	}
	
	

}
