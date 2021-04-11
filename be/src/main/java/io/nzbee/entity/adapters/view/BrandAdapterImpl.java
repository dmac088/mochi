package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.brand.view.BrandFacetViewDTO;
import io.nzbee.entity.brand.view.IBrandFacetService;
import io.nzbee.entity.brand.view.IBrandFacetViewMapper;
import io.nzbee.exceptions.NotFoundException;
import io.nzbee.view.ports.IBrandFacetViewPortService;
import io.nzbee.view.product.brand.BrandFacetView;

public class BrandAdapterImpl implements IBrandFacetViewPortService {

	@Autowired
	private IBrandFacetService brandService;
	
	@Autowired IBrandFacetViewMapper brandMapper;
	
	@Override
	public List<BrandFacetView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice) {
		
		return brandService.findAll(locale, currency, categoryCode, new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(tagCodes), maxPrice)
				.stream().map(b -> brandMapper.toView(b)).collect(Collectors.toList());
	}

	@Override
	public BrandFacetView findByCode(String locale, String rootCategory, String brandCode) {
		BrandFacetViewDTO dto = brandService.findByCode(locale, rootCategory, brandCode)
			.orElseThrow(() -> new NotFoundException("Brand not found for code " + brandCode));
		return brandMapper.toView(dto);
	}

}
