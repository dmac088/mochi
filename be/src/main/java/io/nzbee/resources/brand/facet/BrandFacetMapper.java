package io.nzbee.resources.brand.facet;

import org.springframework.stereotype.Component;

import io.nzbee.enums.FacetNameEnum;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacetMapper;
import io.nzbee.view.product.brand.facet.BrandFacetView;

@Component
public class BrandFacetMapper implements IFacetMapper<BrandFacetView, EntityFacet> {

	public EntityFacet toEntityFacet(BrandFacetView brand) {
		EntityFacet ef = new EntityFacet();
		ef.setFacetingName(FacetNameEnum.brand);
		ef.setObjectType(brand.getClass().getSimpleName());
		ef.setId(brand.getBrandCode());
		ef.setDesc(brand.getBrandDesc());
		ef.setHierarchical(true);
		ef.setValue(brand.getBrandCode());
		ef.setCount(brand.getObjectCount().intValue());
		ef.setHierarchical(false);
		return ef;
	}

}
