package io.nzbee.entity.category;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.ProductCategory;
import java.util.Optional;
import org.springframework.stereotype.Component;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.product.CategoryProduct;

@Component(value="categoryMapper")
public class CategoryMapper implements ICategoryMapper {

	public Optional<io.nzbee.domain.category.Category> entityToDo(Optional<?> e) {
		if(!e.isPresent()) { return Optional.ofNullable(null); }
		Category ce = (Category) e.get();
		io.nzbee.domain.category.Category co = null;
		if(ce instanceof CategoryProduct) {
			co = new ProductCategory(
				ce.getCategoryCode(),
				ce.getCategoryAttribute().getCategoryDesc(),
				true,
				ce.getCategoryLevel(),
				ce.getObjectCount(),
				ce.getParent().isPresent()
				? ce.getParent().get().getCategoryCode()
				: null,
				ce.getLocale(), 
				ce.getCurrency()
			 );
		}
		if(ce instanceof CategoryBrand) {
			co = new BrandCategory(
				ce.getCategoryCode(),
				ce.getCategoryAttribute().getCategoryDesc(),
				true,
				ce.getCategoryLevel(),
				ce.getObjectCount(),
				ce.getParent().isPresent()
				? ce.getParent().get().getCategoryCode()
				: null,
				ce.getLocale(), 
				ce.getCurrency()
				);
		}
		return Optional.ofNullable(co);
	}
	
	@Override
	public Optional<io.nzbee.domain.category.Category> entityToDo(Optional<?> e, String locale, String currency) {
		if(!e.isPresent()) { return Optional.ofNullable(null); }
		Category ce = (Category) e.get();
		io.nzbee.domain.category.Category co = null;
		if(ce instanceof CategoryProduct) {
			co = new ProductCategory(
				ce.getCategoryCode(),
				ce.getCategoryAttribute().getCategoryDesc(),
				true,
				ce.getCategoryLevel(),
				ce.getObjectCount(),
				ce.getParent().isPresent()
				? ce.getParent().get().getCategoryCode()
				: null,
				locale, 
				currency
			);
		}
		if(ce instanceof CategoryBrand) {
			co = new BrandCategory(
				ce.getCategoryCode(),
				ce.getCategoryAttribute().getCategoryDesc(),
				true,
				ce.getCategoryLevel(),
				ce.getObjectCount(),
				ce.getParent().isPresent()
				? ce.getParent().get().getCategoryCode()
				: null,
				locale, 
				currency
			);
		}
		return Optional.ofNullable(co);
	}
}
