package io.nzbee.entity.tag;

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
	
	@Autowired 
	private ITagRepository tagRepository;
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<TagDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Double maxPrice) {
		return productTagDAO.findAll(
									locale, 
									currency, 
									categoryCode,
									categoryCodes, 
									brandCodes,
									maxPrice);
	}

	@Override
	public Set<TagEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TagEntity> findById(Long id) {
		return tagRepository.findById(id);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<TagDTO> findAll(String locale) {
		return productTagDAO.findAll(locale);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<TagDTO> findAll(String locale, Set<String> codes) {
		return productTagDAO.findAll(locale, codes);
	}
	
	@Override
	public Set<TagEntity> findAll(Set<String> codes) {
		return productTagDAO.findAll(codes);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #id}")
	public Optional<TagDTO> findById(String locale, Long id) {
		return productTagDAO.findById(locale, id);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #tagCode}")
	public Optional<TagDTO> findDTOByCode(String locale, String tagCode) {
		return productTagDAO.findByCode(locale, tagCode);
	}
	
	@Override
	public Optional<TagEntity> findEntityByCode(String code) {
		return tagRepository.findByTagCode(code);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #tagDesc}")
	public Optional<TagDTO> findByDesc(String locale, String tagDesc) {
		return productTagDAO.findByDesc(locale, tagDesc);
	}
	
	@Override
	public Optional<TagEntity> findEntityByDesc(String locale, String desc) {
		return tagRepository.findByAttributesLclCdAndAttributesTagDesc(locale, desc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#tagCode")
	public Optional<TagEntity> findByCode(String tagCode) {
		return productTagDAO.findByCode(tagCode);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Set<TagDTO> findAll(String lcl, String currency, Set<String> codes) {
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
			  @CacheEvict(cacheNames = CACHE_NAME, key="#tag.tagCode"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="{#tag.locale, #tag.tagId}"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="{#tag.locale, #tag.tagCode}"),
			  @CacheEvict(cacheNames = CACHE_NAME + "Other", allEntries = true)
			})
	public void save(TagEntity tag) {
		productTagDAO.save(tag);
	}

	@Override
	public void update(TagEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TagEntity t) {
		// TODO Auto-generated method stub
		
	}

}
