package io.nzbee.entity.category.brand;

import org.springframework.stereotype.Component;
import io.nzbee.domain.category.BrandCategory;

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
				e.getLocale(), 
				e.getCurrency()
				);
	}

	@Override
	public CategoryBrand doToEntity(BrandCategory d) {
		// TODO Auto-generated method stub
		return null;
	}

}
