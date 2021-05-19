package io.nzbee.entity.category.product.view.facet;

import org.springframework.data.repository.CrudRepository;
import io.nzbee.entity.category.product.CategoryProductEntity;

public interface IProductCategoryFacetRepository extends CrudRepository<CategoryProductEntity, Long> {
	
	
	
}
