package io.nzbee.resources.brand.browse.facet;

import org.springframework.stereotype.Component;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacetMapper;
import io.nzbee.view.product.brand.facet.BrandFacetView;

@Component
public class BrandBrowseFacetModelMapperImpl implements IFacetMapper<BrandFacetView> {


	@Override
	public EntityFacet toEntityFacet(BrandFacetView brand) {
		EntityFacet ef = new EntityFacet();

		ef.setFacetingName("brand");
		ef.setObjectType(brand.getClass().getSimpleName());
		ef.setId(brand.getBrandCode());
		ef.setDesc(brand.getBrandDesc());
		ef.setHierarchical(true);
		ef.setValue(brand.getBrandCode());
		ef.setCount(brand.getObjectCount().intValue());

		return ef;
	}

}
