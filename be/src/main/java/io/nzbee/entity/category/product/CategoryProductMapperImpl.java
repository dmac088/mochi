package io.nzbee.entity.category.product;

import org.springframework.stereotype.Component;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;

@Component
public class CategoryProductMapperImpl implements ICategoryProductMapper {

	
	@Override
	public ProductCategory entityToDo(CategoryProductDTO e) {
		return new ProductCategory(
				e.getCategoryCode(),
				e.getAttributes().stream().filter(c -> c.getLclCd().equals(e.getLocale())).findAny().get().getCategoryDesc(),
				true,
				e.getCategoryLevel(),
				e.getCount(),
				e.getParent().isPresent()
				? e.getParent().get().getCategoryCode()
				: null,
				e.getChildCount(),
				e.getLocale()
			 );
	}

	@Override
	public CategoryProductDTO doToEntity(ProductCategory d) {
		ProductCategory pc = (ProductCategory) d;
		
		CategoryProductEntity cp = new CategoryProductEntity();
		cp.setCategoryCode(pc.getCategoryCode());
		cp.setLocale(pc.getLocale());
		cp.setCategoryLevel(pc.getCategoryLevel());
		cp.setObjectCount(pc.getCount());
		
		CategoryAttributeEntity ca = new CategoryAttributeEntity();
		ca.setCategoryDesc(pc.getCategoryDesc());
		ca.setLclCd(pc.getLocale());
		
		ca.setCategory(cp);
		cp.addCategoryAttribute(ca);
		
		return cp;
	}

}
