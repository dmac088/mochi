package io.nzbee.entity.category.product.view;

import org.springframework.data.repository.CrudRepository;
import io.nzbee.entity.category.product.CategoryProductEntity;

public interface IProductCategoryRepository extends CrudRepository<CategoryProductEntity, Long> {
	
	
	
}
