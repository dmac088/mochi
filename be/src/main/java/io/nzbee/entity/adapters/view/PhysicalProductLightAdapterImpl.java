package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightMapper;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightService;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDTO;
import io.nzbee.search.ISearchService;
import io.nzbee.search.facet.IFacet;
import io.nzbee.view.ports.IPhysicalProductLightPortService;
import io.nzbee.view.product.physical.PhysicalProductLightView;

public class PhysicalProductLightAdapterImpl implements IPhysicalProductLightPortService {

	@Autowired
	private IPhysicalProductLightService productEntityService;
	
	@Autowired
	private IPhysicalProductLightMapper productLightMapper;
	
	@Autowired
	private ISearchService searchService;

	@Override
	public Page<PhysicalProductLightView> findAll(String locale, String currency,
			String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort) {
		
		return productEntityService.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice, page, size, sort)
				.map(p -> productLightMapper.DTOToView(p));
	}

	
	@Override
	public Page<PhysicalProductLightView> search(String locale, String currency, String categoryCode, int page,
			int size, String sort, String searchTerm, Set<IFacet> selectedFacets, Set<IFacet> returnFacets) {
		return searchService.findAll(locale, currency, categoryCode, searchTerm, page,
				size, sort, selectedFacets, returnFacets);
	}


	@Override
	public Page<PhysicalProductLightView> findAll(String locale, String currency, String categoryCode,
			Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page,
			String size, String sort) {
		return productEntityService.findAll(locale, 
											currency, 
											categoryCode, 
											new StringCollectionWrapper(categoryCodes), 
											new StringCollectionWrapper(brandCodes), 
											new StringCollectionWrapper(tagCodes), 
											maxPrice, 
											page, 
											size, 
											sort)
				.map(p -> productLightMapper.DTOToView(p));
	}


	@Override
	public Page<PhysicalProductLightView> findAllPhysicalProducts(String locale, String currency, String categoryCode,
			Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page,
			String size, String sort) {
		return productEntityService.findAll(locale, 
											currency, 
											categoryCode, 
											new StringCollectionWrapper(categoryCodes), 
											new StringCollectionWrapper(brandCodes), 
											new StringCollectionWrapper(tagCodes), 
											maxPrice, 
											page, 
											size, 
											sort)
				.map(p -> productLightMapper.DTOToView(p));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PhysicalProductLightView> findAll(String locale, String currency, Set<String> codes) {
		List<PhysicalProductLightDTO> lp =  productEntityService.findAll(locale, currency, Constants.defaultProductRootCategoryCode ,new StringCollectionWrapper(codes));
		return lp.stream().map(p -> productLightMapper.DTOToView(p)).collect(Collectors.toList());
	}



	@Override
	public List<PhysicalProductLightView> findAll(String locale, String currency, String rootCategoryCode,
			Set<String> codes) {
		List<PhysicalProductLightDTO> lp =  productEntityService.findAll(locale, currency, rootCategoryCode, new StringCollectionWrapper(codes));
		return lp.stream().map(p -> productLightMapper.DTOToView(p)).collect(Collectors.toList());
	}

	@Override
	public String[] getSuggestion(String searchTerm, String rootCategory, String locale, String currency) {
		return searchService.getSuggestions(searchTerm, rootCategory, locale, currency);
	}

	@Override
	public void save(PhysicalProductLightView viewObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PhysicalProductLightView viewObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PhysicalProductLightView viewObject) {
		// TODO Auto-generated method stub
		
	}


}
