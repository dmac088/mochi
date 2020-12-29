package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import io.nzbee.search.IFacetService;

@Service(value = "tagEntityService")
public class TagServiceImpl implements ITagService, IFacetService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private static final String CACHE_NAME = "tagCache";
	
	@Autowired
	private ITagDao tagDAO;
	
	@Autowired
	private ITagRepository tagRepository;

	@Override
	public List<TagEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TagEntity> findById(Long id) {
		LOGGER.debug("call TagServiceImpl.findById with parameters : {}", id);
		return tagRepository.findById(id);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<TagDTO> findAll(String locale) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}", locale);
		return tagDAO.findAll(locale);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<TagDTO> findAll(String locale, Set<String> codes) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}, {}", locale, StringUtils.join(codes));
		return tagDAO.findAll(locale, codes);
	}
	
	@Override
	public List<TagEntity> findAll(Set<String> codes) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}", StringUtils.join(codes));
		return tagDAO.findAll(codes);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #id}")
	public Optional<TagDTO> findById(String locale, Long id) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}, {}", locale, id);
		return tagDAO.findById(locale, id);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #tagCode}")
	public Optional<TagDTO> findByCode(String locale, String code) {
		LOGGER.debug("call TagServiceImpl.findByCode with parameters : {}, {}", locale, code);
		return tagDAO.findByCode(locale, code);
	}
	
	@Override
	public Optional<TagEntity> findEntityByCode(String code) {
		LOGGER.debug("call TagServiceImpl.findEntityByCode with parameters : {}", code);
		return tagRepository.findByTagCode(code);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #tagDesc}")
	public Optional<TagDTO> findByDesc(String locale, String desc) {
		LOGGER.debug("call TagServiceImpl.findByDesc with parameters : {}, {}", locale, desc);
		return tagDAO.findByDesc(locale, desc);
	}
	
	@Override
	public Optional<TagEntity> findEntityByDesc(String locale, String desc) {
		LOGGER.debug("call TagServiceImpl.findEntityByDesc with parameters : {}, {}", locale, desc);
		return tagRepository.findByAttributesLclCdAndAttributesTagDesc(locale, desc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#tagCode")
	public Optional<TagEntity> findByCode(String code) {
		LOGGER.debug("call TagServiceImpl.findByCode with parameters : {}", code);
		return tagDAO.findByCode(code);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<TagDTO> findAll(String locale, String currency, Set<String> codes) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}, {}, {}", locale, currency, codes);
		return tagDAO.findAll(locale, codes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<TagDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Double maxPrice) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}, {}, {}", locale, currency, categoryCode);
		return tagDAO.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, maxPrice);
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
		tagDAO.save(tag);
	}

	@Override
	public void update(TagEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TagEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<TagEntity> findEntityById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
