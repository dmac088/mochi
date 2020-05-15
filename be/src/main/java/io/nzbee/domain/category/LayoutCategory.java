package io.nzbee.domain.category;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("layoutcategory")
public class LayoutCategory extends Category {

	public LayoutCategory(String categoryCode, String categoryDesc, Long categoryLevel, String lclCd, String currency,
			int objectCount) {
		super(categoryCode, categoryDesc, categoryLevel, lclCd, currency, objectCount);
		// TODO Auto-generated constructor stub
	}

}
