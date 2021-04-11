package io.nzbee.resources.brand;

import org.springframework.stereotype.Component;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacetMapper;
import io.nzbee.view.product.brand.BrandView;

@Component
public class BrandFacetMapperImpl implements IFacetMapper<BrandView> {


	@Override
	public EntityFacet toEntityFacet(BrandView brand) {
		EntityFacet ef = new EntityFacet();
		ef.setFacetingName("brand");
		ef.setObjectType(brand.getClass().getSimpleName());
		ef.setId(brand.getBrandCode());
		ef.setDesc(brand.getBrandDesc());
		ef.setHierarchical(true);
		ef.setValue(brand.getBrandCode());
		return ef;
	}

}