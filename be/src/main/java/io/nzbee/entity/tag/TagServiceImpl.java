package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import io.nzbee.search.IFacetService;

@Service(value = "tagEntityService")
public class TagServiceImpl implements ITagService, IFacetService {

	private static final String CACHE_NAME = "tagCache";
	
	@Autowired
	private ITagDao productTagDAO;
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<Tag> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Double maxPrice) {
		return productTagDAO.findAll(
									locale, 
									currency, 
									categoryCode,
									categoryCodes, 
									brandCodes,
									maxPrice);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Tag> findAll(String locale) {
		return productTagDAO.findAll(locale);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Tag> findAll(String locale, Set<String> codes) {
		return productTagDAO.findAll(locale, codes);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #id}")
	public Optional<Tag> findById(String locale, long id) {
		return productTagDAO.findById(locale, id);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #code}")
	public Optional<Tag> findByCode(String locale, String code) {
		return productTagDAO.findByCode(locale, code);
	}

	@Override
	public Optional<Tag> findByDesc(String locale, String desc) {
		return productTagDAO.findByDesc(locale, desc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#code}")
	public Optional<Tag> findByCode(String code) {
		return productTagDAO.findByCode(code);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<Tag> findAll(String lcl, String currency, Set<String> codes) {
		return productTagDAO.findAll(lcl, codes);
	}
	
	@Override
	public String tokenToCode(String token) {
		return token;
	}

	@Override
	public String getFacetField() {
		return "product.tags.tagToken";
	}

	@Override
	public String getFacetCategory() {
		return "tag";
	}

	@Override
	@Caching(evict = {
			  @CacheEvict(cacheNames = CACHE_NAME, key="{#tag.tagCode}"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="{#tag.locale, #tag.tagId}"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="{#tag.locale, #tag.tagCode}"),
			  @CacheEvict(cacheNames = CACHE_NAME + "Other", 			allEntries = true)
			})
	public void save(Tag tag) {
		productTagDAO.save(tag);
	}

	@Override
	public void update(Tag t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tag t) {
		// TODO Auto-generated method stub
		
	}
	
}
