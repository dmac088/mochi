package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.tomcat.util.buf.StringUtils;
import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.IFacetService;

@Service(value = "tagEntityService")
public class TagServiceImpl implements ITagService, IFacetService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private static final String CACHE_NAME = "tagCache";
	
	@Autowired
	private ITagDao tagDao;
	
	@Autowired
	private ITagRepository tagRepository;

	
	//Entity fetch
	@Override
	public List<TagEntity> findAll() {
		return tagRepository.findAll();
	}
	
	//Entity fetch
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#id.toString()")
	public Optional<TagEntity> findById(Long id) {
		LOGGER.debug("call TagServiceImpl.findById with parameters : {}", id);
		return tagRepository.findById(id);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#code")
	public Optional<TagEntity> findByCode(String code) {
		LOGGER.debug("call TagServiceImpl.findByCode with parameters : {}", code);
		return tagDao.findByCode(code);
	}
	
	@Override
	//@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<TagEntity> findAll(Set<String> codes) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}", StringUtils.join(codes));
		return tagDao.findAll(codes);
	}
	
	//DTO Fetch
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #id.toString()")
	public Optional<TagDTO> findById(String locale, Long id) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}, {}", locale, id);
		return tagDao.findById(locale, id);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #code")
	public Optional<TagDTO> findByCode(String locale, String code) {
		LOGGER.debug("call TagServiceImpl.findByCode with parameters : {}, {}", locale, code);
		return tagDao.findByCode(locale, code);
	}
	
	@Override
	public Optional<TagDTO> findByDesc(String locale, String desc) {
		LOGGER.debug("call TagServiceImpl.findByDesc with parameters : {}, {}", locale, desc);
		return tagDao.findByDesc(locale, desc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<TagDTO> findAll(String locale) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}", locale);
		return tagDao.findAll(locale);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<TagDTO> findAll(String locale, StringCollectionWrapper codes) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}, {}", locale, StringUtils.join(codes.getCodes()));
		return tagDao.findAll(locale, codes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<TagDTO> findAll(String locale, String currency, StringCollectionWrapper codes) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}, {}, {}", locale, currency, codes.getCodes());
		return tagDao.findAll(locale, codes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString())")
	public List<TagDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, Double maxPrice) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : locale = {}, currency = {}, categoryCode = {}, categoryCodes = {}, brandCodes = {}, maxPrice = {}", locale, currency, categoryCode, StringUtil.join(categoryCodes, ','), StringUtil.join(brandCodes, ','), maxPrice);
		
		return tagDao.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, maxPrice);
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
			  @CacheEvict(cacheNames = CACHE_NAME, key="#tag.tagId.toString()"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#tag.tagCode"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#tag.locale + \", \" + #tag.tagId.toString()"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#tag.locale + \", \" + #tag.tagCode"),
			  @CacheEvict(cacheNames = CACHE_NAME + "Other", allEntries = true)
			})
	public void save(TagEntity tag) {
		tagDao.save(tag);
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
