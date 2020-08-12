package io.nzbee.entity.category.product;

import org.springframework.stereotype.Component;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.entity.category.attribute.CategoryAttribute;

@Component
public class CategoryProductMapperImpl implements ICategoryProductMapper {

	
	@Override
	public ProductCategory entityToDo(CategoryProduct e) {
		return new ProductCategory(
				e.getCategoryCode(),
				e.getCategoryAttribute().getCategoryDesc(),
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
	public CategoryProduct doToEntity(ProductCategory d) {
		ProductCategory pc = (ProductCategory) d;
		
		CategoryProduct cp = new CategoryProduct();
		cp.setCategoryCode(pc.getCategoryCode());
		cp.setLocale(pc.getLocale());
		cp.setCategoryLevel(pc.getCategoryLevel());
		cp.setObjectCount(pc.getCount());
		
		CategoryAttribute ca = new CategoryAttribute();
		ca.setCategoryDesc(pc.getCategoryDesc());
		ca.setLclCd(pc.getLocale());
		
		ca.setCategory(cp);
		cp.addCategoryAttribute(ca);
		
		return cp;
	}

}
