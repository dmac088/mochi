package io.nzbee.entity.category.product;

import org.springframework.stereotype.Component;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;

@Component
public class CategoryProductMapperImpl implements ICategoryProductMapper {

	@Override
	public ProductCategory DTOToDo(CategoryProductDTO dto) {
		return new ProductCategory(
				dto.getCategoryCode(),
				dto.getCategoryDesc(),
				true,
				dto.getCategoryLevel(),
				dto.getProductCount(),
				((dto.getParentCategory() == null) ? null : dto.getParentCategory().getCategoryCode()),
				dto.getChildCategoryCount(),
				dto.getLocale()
			 );
	}

	@Override
	public CategoryProductEntity doToEntity(ProductCategory d) {
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
