package io.nzbee.entity.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.entity.category.brand.CategoryBrandDTO;
import io.nzbee.entity.category.brand.ICategoryBrandMapper;
import io.nzbee.entity.category.product.CategoryProductDTO;
import io.nzbee.entity.category.product.ICategoryProductMapper;

@Component(value="categoryMapper")
public class CategoryMapperImpl implements ICategoryMapper {
	
	@Autowired
	private ICategoryProductMapper categoryProductMapper;
	
	@Autowired
	private ICategoryBrandMapper categoryBrandMapper;
	
	public io.nzbee.domain.category.Category entityToDo(CategoryDTO e) {
	
		if(e instanceof CategoryProductDTO) {
			return categoryProductMapper.entityToDo((CategoryProductDTO) e);
		}
		if(e instanceof CategoryBrandDTO) {
			return categoryBrandMapper.entityToDo((CategoryBrandDTO) e);
		}
		return null;
		
	}

	@Override
	public CategoryDTO doToEntity(io.nzbee.domain.category.Category d) {
		return null;
	}

}
