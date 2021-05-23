package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.tag.view.facet.ITagFacetDTOService;
import io.nzbee.entity.tag.view.facet.ITagFacetMapper;
import io.nzbee.view.ports.ITagFacetViewPortService;
import io.nzbee.view.product.tag.facet.TagFacetView;

@Component
public class TagFacetAdapterImpl  implements ITagFacetViewPortService {

	@Autowired 
	private ITagFacetDTOService tagService;
	
	@Autowired
	private ITagFacetMapper tagMapper;
	

	@Override
	@Transactional(readOnly = true)
	public List<TagFacetView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Double maxPrice) {
		return tagService.findAll(locale, currency, categoryCode, new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(brandCodes), maxPrice)
				.stream().map(b -> (TagFacetView) tagMapper.DTOToDo(b)).collect(Collectors.toList());
	}


}
