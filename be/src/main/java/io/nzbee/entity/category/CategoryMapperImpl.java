package io.nzbee.entity.category;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.ProductCategory;
import org.springframework.stereotype.Component;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.product.CategoryProduct;

@Component(value="categoryMapper")
public class CategoryMapperImpl implements ICategoryMapper {
	

	public io.nzbee.domain.category.Category entityToDo(Category e) {
	
		if(e instanceof CategoryProduct) {
			io.nzbee.domain.category.Category co = new ProductCategory(
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
			return co;
		}
		if(e instanceof CategoryBrand) {
			io.nzbee.domain.category.Category co = new BrandCategory(
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
			return co;
		}
		return null;
	}
	
	@Override
	public io.nzbee.domain.category.Category entityToDo(Category e, String locale, String currency) {
		if(e instanceof CategoryProduct) {
			io.nzbee.domain.category.Category co = new ProductCategory(
				e.getCategoryCode(),
				e.getCategoryAttribute().getCategoryDesc(),
				true,
				e.getCategoryLevel(),
				e.getObjectCount(),
				e.getParent().isPresent()
				? e.getParent().get().getCategoryCode()
				: null,
				locale, 
				currency
			);
			return co;
		}
		if(e instanceof CategoryBrand) {
			io.nzbee.domain.category.Category co = new BrandCategory(
				e.getCategoryCode(),
				e.getCategoryAttribute().getCategoryDesc(),
				true,
				e.getCategoryLevel(),
				e.getObjectCount(),
				e.getParent().isPresent()
				? e.getParent().get().getCategoryCode()
				: null,
				locale, 
				currency
			);
			return co;
		}
		return null;
	}
}
