package io.nzbee.entity.category;
import io.nzbee.domain.category.Category;

import org.springframework.stereotype.Component;

import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.entity.category.product.CategoryProduct;

@Component(value="categoryMapper")
public class CategoryMapper implements ICategoryMapper {

	public Category entityToDo(io.nzbee.entity.category.Category e) {
		if(e instanceof CategoryProduct) {
			return new ProductCategory(
					e.getCategoryCode(),
					e.getCategoryAttribute().getCategoryDesc(),
					true,
					e.getCategoryLevel(),
					e.getCategoryType().getCategoryTypeDesc(),
					e.getObjectCount(),
					e.getParent().isPresent()
					? e.getParent().get().getCategoryCode()
					: null,
					e.getLocale(), 
					e.getCurrency()
			);
		}
		return new BrandCategory(
				e.getCategoryCode(),
				e.getCategoryAttribute().getCategoryDesc(),
				true,
				e.getCategoryLevel(),
				e.getCategoryType().getCategoryTypeDesc(),
				e.getObjectCount(),
				e.getParent().isPresent()
				? e.getParent().get().getCategoryCode()
				: null,
				e.getLocale(), 
				e.getCurrency()
		);
	}
	
	@Override
	public Category entityToDo(io.nzbee.entity.category.Category e, String locale, String currency) {
		if(e instanceof CategoryProduct) {
			return new ProductCategory(
					e.getCategoryCode(),
					e.getAttributes().stream().findFirst().get().getCategoryDesc(),
					true,
					e.getCategoryLevel(),
					e.getCategoryType().getCategoryTypeDesc(),
					e.getObjectCount(),
					e.getParent().isPresent()
					? e.getParent().get().getCategoryCode()
					: null,
					locale, 
					currency
			);
		}
		return new BrandCategory(
				e.getCategoryCode(),
				e.getCategoryAttribute().getCategoryDesc(),
				true,
				e.getCategoryLevel(),
				e.getCategoryType().getCategoryTypeDesc(),
				e.getObjectCount(),
				e.getParent().isPresent()
				? e.getParent().get().getCategoryCode()
				: null,
				locale, 
				currency
		);
	}
	
}
