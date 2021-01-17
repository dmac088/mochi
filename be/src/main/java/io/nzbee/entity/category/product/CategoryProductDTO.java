package io.nzbee.entity.category.product;

import java.util.Map;
import io.nzbee.entity.category.CategoryDTO;

public class CategoryProductDTO extends CategoryDTO {

	private static final long serialVersionUID = -2047877371295351601L;

	public static final String LEVEL_ALIAS = "cat_lvl";
    
    public static final String PRODUCT_COUNT_ALIAS = "object_count";
    
    public static final String CHILD_COUNT_ALIAS = "child_cat_count";
	
	private Long categoryLevel;
	
	private Long productCount;
	
	private CategoryProductParentDTO parentCategory;
	
	private Long childCategoryCount;


	public CategoryProductDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.categoryLevel 			= ((Number) tuple[aliasToIndexMap.get(LEVEL_ALIAS)]).longValue();
		this.parentCategory 		= !(tuple[aliasToIndexMap.get(CategoryProductParentDTO.ID_ALIAS)] == null)  
									  ? new CategoryProductParentDTO(tuple, aliasToIndexMap)
									  : null;
		this.productCount			= !(aliasToIndexMap.get(PRODUCT_COUNT_ALIAS) == null)
									  ? ((Number) tuple[aliasToIndexMap.get(PRODUCT_COUNT_ALIAS)]).longValue()
									  : new Long(0);
		this.childCategoryCount    	= !(aliasToIndexMap.get(CHILD_COUNT_ALIAS) == null)
									  ? ((Number) tuple[aliasToIndexMap.get(CHILD_COUNT_ALIAS)]).longValue()
									  : new Long(0);
	}
	

	public Long getCategoryLevel() {
		return categoryLevel;
	}

	public Long getProductCount() {
		return productCount;
	}

	public CategoryProductParentDTO getParentCategory() {
		return parentCategory;
	}

	public Long getChildCategoryCount() {
		return childCategoryCount;
	}
	
}
