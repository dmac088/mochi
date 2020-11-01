package io.nzbee.entity.category.brand;

import java.util.Map;

import io.nzbee.entity.category.CategoryDTO;

public class CategoryBrandDTO extends CategoryDTO {

	public static final String BRAND_COUNT_ALIAS = "object_count";
	
	private Long brandCount;
	
	public CategoryBrandDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.brandCount    		= ((Number) tuple[aliasToIndexMap.get(BRAND_COUNT_ALIAS)]).longValue();
	}

	public Long getBrandCount() {
		return brandCount;
	}
	
}
