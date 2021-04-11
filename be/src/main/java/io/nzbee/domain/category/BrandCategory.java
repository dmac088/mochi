package io.nzbee.domain.category;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("brandcategory")
public class BrandCategory extends Category {

	public BrandCategory(	String categoryCode,
							String categoryDesc,
							boolean isHierarchical,
							Long objectCount,
							String locale) {
		super(categoryCode, 
			  categoryDesc, 
			  locale, 
			  objectCount);
		this.categoryType = this.getClass().getSimpleName().toString().toLowerCase();
	}
	
	public BrandCategory(	String categoryCode,
							String categoryDesc,
							String locale) {
		super(	categoryCode, 
				categoryDesc, 
				locale);
		
		this.categoryType = this.getClass().getSimpleName().toString().toLowerCase();

	}

	
}
