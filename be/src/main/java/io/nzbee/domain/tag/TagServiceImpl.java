package io.nzbee.domain.tag;

import java.util.List;
import java.util.Set;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
//The Domain object service should be simple and dumb
//it received primitive types and responds with domain objects,
//that are constructed from relevant entity objects
//its simplicity helps us with unit testing
import io.nzbee.domain.IFacetService;

@Service(value = "tagDomainService")
@CacheConfig(cacheNames="tags")
public class TagServiceImpl implements ITagService, IFacetService {

	@Autowired
	io.nzbee.domain.adapters.ICategoryPortService categoryService;
	
	@Autowired
	io.nzbee.domain.adapters.ITagPortService tagService;
	
	@Autowired
	io.nzbee.domain.adapters.IProductPortService productService;
	
	@Override
	@Transactional
	public Tag findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub	
		return tagService.findByCode(locale, currency, code);
		
	}

	@Override
	@Transactional
	public Tag findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return tagService.findByDesc(locale, currency, desc);
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Tag> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Set<Tag> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return tagService.findAll(locale, currency, codes);
	}
	
	@Override
	public String tokenToCode(String token) {
		// TODO Auto-generated method stub
		return token;
	}

	@Override
	public String getFacetField() {
		// TODO Auto-generated method stub
		return "product.tags.tagToken";
	}

	@Override
	public String getFacetCategory() {
		// TODO Auto-generated method stub
		return "tag";
	}
}
