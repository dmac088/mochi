package io.nzbee.domain.tag;

import java.util.Set;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import io.nzbee.domain.IFacetService;

@Service(value = "tagDomainService")
@CacheConfig(cacheNames="tags")
public class TagServiceImpl implements ITagService, IFacetService {

	@Autowired
	io.nzbee.domain.ports.ICategoryPortService categoryService;
	
	@Autowired
	io.nzbee.domain.ports.ITagPortService tagService;
	
	@Autowired
	io.nzbee.domain.ports.IProductPortService productService;
	
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

	@Override
	public Set<Tag> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return tagService.findAll(locale, currency, codes);
	}
}
