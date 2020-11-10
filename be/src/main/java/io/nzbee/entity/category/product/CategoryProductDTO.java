package io.nzbee.entity.category.product;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;

import io.nzbee.entity.category.CategoryDTO;

public class CategoryProductDTO extends CategoryDTO {

    public static final String LEVEL_ALIAS = "cat_lvl";
    
    public static final String PRODUCT_COUNT_ALIAS = "object_count";
    
    public static final String CHILD_COUNT_ALIAS = "child_cat_count";
	
	private Long categoryLevel;
	
	private Long productCount;
	
	private Optional<CategoryProductParentDTO> parentCategory;
	
	private Long childCategoryCount;
	
	
	public CategoryProductDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.categoryLevel 			= ((BigInteger) tuple[aliasToIndexMap.get(LEVEL_ALIAS)]).longValue();
		//this.productCount    		= ((Number) tuple[aliasToIndexMap.get(PRODUCT_COUNT_ALIAS)]).longValue();
		this.parentCategory 		= Optional.ofNullable(!(tuple[aliasToIndexMap.get(CategoryProductParentDTO.ID_ALIAS)] == null)  
									  ? new CategoryProductParentDTO(tuple, aliasToIndexMap)
									  : null);
		//this.childCategoryCount    	= ((BigInteger) tuple[aliasToIndexMap.get(CHILD_COUNT_ALIAS)]).longValue();
	}
	

	public Long getCategoryLevel() {
		return categoryLevel;
	}

	public Long getProductCount() {
		return productCount;
	}

	public Optional<CategoryProductParentDTO> getParentCategory() {
		return parentCategory;
	}

	public Long getChildCategoryCount() {
		return childCategoryCount;
	}
	
}
