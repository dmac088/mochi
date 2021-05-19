package io.nzbee.entity.category.product.view.facet;

import org.springframework.data.repository.CrudRepository;
import io.nzbee.entity.category.product.CategoryProductEntity;

public interface IProductCategoryFacetDTORepository extends CrudRepository<CategoryProductEntity, Long> {
	
	
	
}
