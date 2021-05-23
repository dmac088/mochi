package io.nzbee.view.product.tag.facet;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.view.ports.ITagFacetViewPortService;

public class TagFacetViewServiceImpl implements ITagFacetViewService {

	@Autowired
	private ITagFacetViewPortService tagService;
	

	@Override
	public List<TagFacetView> findAll(	String locale, 
								String currency, 
								String categoryCode, 
								Set<String> categories,
								Set<String> brands, 
								Double maxPrice) {
		return tagService.findAll(	 locale, 
				 currency, 
				 categoryCode,
				 categories,
				 brands,
				 maxPrice    							  
		);
	}

}
