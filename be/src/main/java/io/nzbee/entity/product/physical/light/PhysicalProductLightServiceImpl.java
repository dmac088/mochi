package io.nzbee.entity.product.physical.light;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.IProductDao;
import io.nzbee.entity.product.physical.PhysicalProductEntity;

@Service
public class PhysicalProductLightServiceImpl implements IPhysicalProductLightService {

	@Autowired
	private IProductDao productDAO;
	
	@Override
	public Page<PhysicalProductLightDTO> findAll(String locale, String currency, String rootCategory,
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
		 ).map(p -> (PhysicalProductLightDTO) p);
	}

	@Override
	public List<PhysicalProductLightDTO> findAll(String locale, String currency, String rootCategoryCode,
			StringCollectionWrapper productCodes) {
		return productDAO.findAll(locale, currency, rootCategoryCode, productCodes).stream()
				.map(p -> (PhysicalProductLightDTO) p).collect(Collectors.toList());
	}



}
