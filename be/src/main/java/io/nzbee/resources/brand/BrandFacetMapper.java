package io.nzbee.resources.brand;

import org.springframework.stereotype.Component;

import io.nzbee.domain.brand.Brand;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacetMapper;

@Component
public class BrandFacetMapper implements IFacetMapper<Brand> {

	public EntityFacet toEntityFacet(Brand brand) {
		EntityFacet ef = new EntityFacet();
		ef.setFacetingName("brand");
		ef.setObjectType(brand.getClass().getSimpleName());
		ef.setId(brand.getBrandCode());
		ef.setDesc(brand.getBrandDesc());
		ef.setHierarchical(true);
		ef.setValue(brand.getBrandCode());
		ef.setCount(brand.getCount().intValue());
		return ef;
	}

}
