package io.nzbee.entity.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.brand.ICategoryBrandMapper;
import io.nzbee.entity.category.layout.CategoryLayout;
import io.nzbee.entity.category.layout.ICategoryLayoutMapper;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.ICategoryProductMapper;

@Component(value="categoryMapper")
public class CategoryMapperImpl implements ICategoryMapper {
	
	@Autowired
	private ICategoryProductMapper categoryProductMapper;
	
	@Autowired
	private ICategoryBrandMapper categoryBrandMapper;
	
	@Autowired
	private ICategoryLayoutMapper categoryLayoutMapper;

	public io.nzbee.domain.category.Category entityToDo(Category e) {
	
		if(e instanceof CategoryProduct) {
			return categoryProductMapper.entityToDo((CategoryProduct) e);
		}
		if(e instanceof CategoryBrand) {
			return categoryBrandMapper.entityToDo((CategoryBrand) e);
		}
		if(e instanceof CategoryLayout) {
			return categoryLayoutMapper.entityToDo((CategoryLayout) e);
		}
		return null;
		
	}

	@Override
	public Category doToEntity(io.nzbee.domain.category.Category d) {
		
		return null;
	}
	
}
