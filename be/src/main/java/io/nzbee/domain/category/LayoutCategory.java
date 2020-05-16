package io.nzbee.domain.category;

import com.fasterxml.jackson.annotation.JsonTypeName;


/*
 * LayoutCategory exists simply as an abstract container to group a set of unrelated categories i.e.
 * brandcategory, productcategory, promotion category for the purpose of rendering these categories in fixed layouts
 * on the front-end, for example the main header menu is a react component, responsible for rendering each of the menus at the 
 * top of the page, an API call to fetchCategoriesByLayoutCategoryCode(String layoutCode) will allow this parent component 
 * to fetch all the categories in one go, and pass the result to respective child components for rendering,
 * no order will be imposed on the menus since that is front-end concern, the back-end is simply providing a mechanism 
 * to group our categories, since it's so generic we may be better off calling it something like genericCategory.
 * also remember that productcategory is hierarchical while brandcategory is not, therefor the productcategory payload 
 * will be more verbose (we get the children too) 
 */
@JsonTypeName("layoutcategory")
public class LayoutCategory extends Category {

	private Long orderNumber;
	
	public LayoutCategory(String categoryCode, 
						  String categoryDesc, 
						  Long categoryLevel, 
						  String lclCd, 
						  String currency,
						  int objectCount,
						  Long orderNumber) {
		
		super(	categoryCode, 
				categoryDesc, 
				categoryLevel, 
				lclCd, 
				currency, 
				objectCount);
		
		this.orderNumber = orderNumber;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

}
