package io.nzbee.view.product.tag.facet;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.ITagPortService;

public class TagFacetServiceImpl implements ITagFacetService {

	@Autowired
	private ITagPortService tagService;
	
	@Override
	public TagFacetView findByCode(String locale, String code) {
		return tagService.findByCode(locale, code);
	}

	@Override
	public TagFacetView findByDesc(String locale, String desc) {
		return tagService.findByDesc(locale, desc);
	}

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
	
	@Override
	public List<TagFacetView> findAll(String locale) {
		return tagService.findAll(locale);
	}

	@Override
	public List<TagFacetView> findAll(String locale, Set<String> codes) {
		return tagService.findAll(locale, codes);
	}

	@Override
	public void save(TagFacetView object) {
		tagService.save(object);
	}

	@Override
	public void delete(TagFacetView object) {
		tagService.delete(object);
	}

	@Override
	public void update(TagFacetView object) {
		tagService.update(object);
	}


}
