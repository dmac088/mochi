package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.brand.view.facet.BrandFacetViewDTO;
import io.nzbee.entity.brand.view.facet.IBrandFacetViewMapper;
import io.nzbee.entity.brand.view.facet.IBrandFacetViewService;
import io.nzbee.exceptions.NotFoundException;
import io.nzbee.view.ports.IBrandFacetViewPortService;
import io.nzbee.view.product.brand.facet.BrandFacetView;

public class BrandFacetAdapterImpl implements IBrandFacetViewPortService {
	
	@Autowired
	private IBrandFacetViewService brandFacetService;
	
	@Autowired 
	private IBrandFacetViewMapper brandFacetMapper;
		
	@Override
	public List<BrandFacetView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice) {
		
		return brandFacetService.findAll(locale, currency, categoryCode, new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(tagCodes), maxPrice)
				.stream().map(b -> brandFacetMapper.toView(b)).collect(Collectors.toList());
	}

	@Override
	public BrandFacetView findByCode(String locale, String rootCategory, String brandCode) {
		BrandFacetViewDTO dto = brandFacetService.findByCode(locale, rootCategory, brandCode)
			.orElseThrow(() -> new NotFoundException("Brand not found for code " + brandCode));
		return brandFacetMapper.toView(dto);
	}

}
