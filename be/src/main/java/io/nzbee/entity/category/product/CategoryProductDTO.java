package io.nzbee.entity.category.product;

import java.util.Map;

import io.nzbee.entity.category.CategoryDTO;

public class CategoryProductDTO extends CategoryDTO {

    public static final String LEVEL_ALIAS = "cat_lvl";
    
    public static final String PRODUCT_COUNT_ALIAS = "object_count";
    
    public static final String CHILD_COUNT_ALIAS = "child_cat_count";
	
	private Long categoryLevel;
	
	private Long productCount;
	
	private CategoryProductDTO parentCategory;
	
	private Long childCategoryCount;
	
	
	public CategoryProductDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.categoryLevel 			= ((Number) tuple[aliasToIndexMap.get(LEVEL_ALIAS)]).longValue();
		this.productCount    		= ((Number) tuple[aliasToIndexMap.get(PRODUCT_COUNT_ALIAS)]).longValue();
		this.parentCategory 		= new CategoryProductDTO(tuple, aliasToIndexMap);
		this.childCategoryCount    	= ((Number) tuple[aliasToIndexMap.get(CHILD_COUNT_ALIAS)]).longValue();
	}
	

	public Long getCategoryLevel() {
		return categoryLevel;
	}

	public Long getProductCount() {
		return productCount;
	}

	public CategoryProductDTO getParentCategory() {
		return parentCategory;
	}

	public Long getChildCategoryCount() {
		return childCategoryCount;
	}
	
}