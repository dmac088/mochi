package io.nzbee.entity.category.brand;

import io.nzbee.domain.category.BrandCategory;
import io.nzbee.entity.IMapper;

public interface ICategoryBrandMapper extends IMapper<BrandCategory, io.nzbee.entity.category.brand.CategoryBrand> { 

	CategoryBrand entityToDo(io.nzbee.entity.category.brand.CategoryBrand e);

	CategoryBrand entityToDo(io.nzbee.entity.category.brand.CategoryBrand e, String locale, String currency);
	
}
