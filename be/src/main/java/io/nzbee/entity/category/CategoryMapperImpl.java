package io.nzbee.entity.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.domain.category.Category;
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

	@Override
	public CategoryEntity doToEntity(io.nzbee.domain.category.Category d) {
		return null;
	}

	@Override
	public Category DTOToDo(CategoryDTO dto) {
		System.out.println(dto.getCategoryDesc());
		System.out.println(dto.getClass());
		if(dto instanceof CategoryProductDTO) {
			return categoryProductMapper.DTOToDo((CategoryProductDTO) dto);
		}
		if(dto instanceof CategoryBrandDTO) {
			return categoryBrandMapper.DTOToDo((CategoryBrandDTO) dto);
		}
		return null;
	}

}
