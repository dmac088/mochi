package io.nzbee.entity.category.brand;

import java.util.Map;

import io.nzbee.entity.category.CategoryDTO;

public class CategoryBrandDTO extends CategoryDTO {

	public static final String BRAND_COUNT_ALIAS = "object_count";
	
	private Long brandCount;
	
	public CategoryBrandDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.brandCount    		= !(aliasToIndexMap.get(BRAND_COUNT_ALIAS) == null)
								  ? ((Number) tuple[aliasToIndexMap.get(BRAND_COUNT_ALIAS)]).longValue()
								  : new Long(0);
	}

	public Long getBrandCount() {
		return brandCount;
	}
	
}
