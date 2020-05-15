package io.nzbee.entity.category.product;

import org.springframework.stereotype.Component;

import io.nzbee.domain.category.ProductCategory;

@Component
public class CategoryProductMapperImpl implements ICategoryProductMapper {

	@Override
	public ProductCategory entityToDo(CategoryProduct e) {
		return new ProductCategory(
				e.getCategoryCode(),
				e.getCategoryAttribute().getCategoryDesc(),
				true,
				e.getCategoryLevel(),
				e.getObjectCount(),
				e.getParent().isPresent()
				? e.getParent().get().getCategoryCode()
				: null,
				e.getLocale(), 
				e.getCurrency()
			 );
	}

	@Override
	public CategoryProduct doToEntity(ProductCategory d) {
		// TODO Auto-generated method stub
		return null;
	}

}
