package io.nzbee.entity.category.brand;

import java.io.Serializable;
import java.util.Map;

import io.nzbee.entity.category.CategoryDTO;

public class CategoryBrandDTO extends CategoryDTO implements Serializable {

	private static final long serialVersionUID = -1050071867499063932L;

	public static final String BRAND_COUNT_ALIAS = "object_count";
	
	private Long brandCount;
	
	public CategoryBrandDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		this.brandCount    		= !(aliasToIndexMap.get(BRAND_COUNT_ALIAS) == null)
								  ? ((Number) tuple[aliasToIndexMap.get(BRAND_COUNT_ALIAS)]).longValue()
								  : Long.valueOf(0);
	}

	public Long getBrandCount() {
		return brandCount;
	}

	@Override
	public String getDesc() {
		return this.getCategoryDesc();
	}

	@Override
	public Long getCount() {
		return this.getBrandCount();
	}
	
}
