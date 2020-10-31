package io.nzbee.entity.category.brand;

import org.springframework.stereotype.Component;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;

@Component
public class CategoryBrandMapperImpl implements ICategoryBrandMapper {

	@Override
	public BrandCategory entityToDo(CategoryBrand e) {
		return new BrandCategory(
				e.getCategoryCode(),
				e.getCategoryAttribute().getCategoryDesc(),
				true,
				e.getCategoryLevel(),
				e.getObjectCount(),
				e.getLocale()
				);
	}

	@Override
	public CategoryBrand doToEntity(BrandCategory d) {
		BrandCategory bc = (BrandCategory) d;
		
		CategoryBrand cb = new CategoryBrand();
		cb.setCategoryCode(bc.getCategoryCode());
		cb.setLocale(bc.getLocale());
		cb.setObjectCount(bc.getCount());
		
		CategoryAttributeEntity ca = new CategoryAttributeEntity();
		ca.setCategoryDesc(bc.getCategoryDesc());
		ca.setLclCd(bc.getLocale());
		
		ca.setCategory(cb);
		cb.addCategoryAttribute(ca);
		return cb;
	}

}
