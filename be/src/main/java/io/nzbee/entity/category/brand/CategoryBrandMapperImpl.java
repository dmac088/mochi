package io.nzbee.entity.category.brand;

import org.springframework.stereotype.Component;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;

@Component
public class CategoryBrandMapperImpl implements ICategoryBrandMapper {

	@Override
	public BrandCategory entityToDo(CategoryBrandDTO e) {
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
	public CategoryBrandEntity doToEntity(BrandCategory d) {
		BrandCategory bc = (BrandCategory) d;
		
		CategoryBrandEntity cb = new CategoryBrandEntity();
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
