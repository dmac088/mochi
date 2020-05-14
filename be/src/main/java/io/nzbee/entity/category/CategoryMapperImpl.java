package io.nzbee.entity.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.brand.ICategoryBrandMapper;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.ICategoryProductMapper;

@Component(value="categoryMapper")
public class CategoryMapperImpl implements ICategoryMapper {
	
	@Autowired
	private ICategoryProductMapper categoryProductMapper;
	
	@Autowired
	private ICategoryBrandMapper categoryBrandMapper;

	public io.nzbee.domain.category.Category entityToDo(Category e) {
	
		if(e instanceof CategoryProduct) {
			return categoryProductMapper.entityToDo((CategoryProduct) e);
		}
		if(e instanceof CategoryBrand) {
			return categoryBrandMapper.entityToDo((CategoryBrand) e);
		}
		return null;
	}
	
}
