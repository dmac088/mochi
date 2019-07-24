package io.nzbee.domain.services.category;


import java.util.List;

import io.nzbee.domain.Category;
import io.nzbee.ui.web.component.sidebar.SidebarDto;

public interface ICategoryService {
	 
	List<Category> getCategories(String lcl, String currency);
	 
	List<Category> getCategoriesForLevel(String lcl, String currency, Long level);
	 
	List<Category> getCategoryParent(String lcl, String currency, Long parentCategoryId);
	 
	Category getCategory(String lcl, String currency, Long categoryId);
	 
	Category getCategory(String lcl, String currency, String categoryDesc);

	List<SidebarDto> getCategories(String hierarchyCode, String locale, String currency, String categoryDesc, List<SidebarDto> facets);

}
