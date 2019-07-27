package io.nzbee.ui.component.web.sidebar;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Tag;
import io.nzbee.domain.services.tag.ITagService;
import io.nzbee.ui.component.web.generic.UIService;
import io.nzbee.variables.CategoryVars;

public class SidebarServiceImpl extends UIService implements ISidebarService {

	@Autowired
	private ITagService tagService;
	
	@Override
	public List<Sidebar> findAll(String locale, String currency, String category, List<Sidebar> selectedFacets) {
		// TODO Auto-generated method stub
		List<Long> categoryIds = super.getFacetIds(selectedFacets, Category.class);
		List<Long> brandIds = super.getFacetIds(selectedFacets, Brand.class);
		
		List<Tag> tags = tagService.findAll(locale, currency, category, categoryIds, brandIds);
		
		List<Sidebar> tagBars = tags.stream().map(t -> convertTagToSidebar(t)).collect(Collectors.toList());
		
		return tagBars;
	}

	private Sidebar convertTagToSidebar(Tag t) {
		Sidebar s = new Sidebar();
		s.setId(t.getTagId());
		s.setDesc(t.getTagDesc());
		s.setFacetingName(CategoryVars.TAG_FACET_NAME);
		s.setFacetingClassName(t.getClass().getSimpleName());
		s.setProductCount(t.getProductCount());
		return s;
	}
	
}
