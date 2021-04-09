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

import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.IFacetService;

@Service(value = "tagEntityService")
public class TagServiceImpl implements ITagService, IFacetService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public static final String CACHE_NAME = "tagCache";
	
	@Autowired
	private ITagDao tagDao;
	
	@Autowired
	private ITagRepository tagRepository;

	@Override
	public Optional<TagEntity> findById(Long id) {
		LOGGER.debug("call TagServiceImpl.findById with parameters : {}", id);
		return tagRepository.findById(id);
	}
	
	@Override
	public Optional<TagEntity> findByCode(String code) {
		LOGGER.debug("call TagServiceImpl.findByCode with parameters : {}", code);
		return tagDao.findByCode(code);
	}
	
	@Override
	public List<TagEntity> findAll() {
		return tagRepository.findAll();
	}
	
	@Override
	public List<TagEntity> findAll(Set<String> codes) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}", StringUtils.join(codes));
		return tagDao.findAll(codes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #rootCategory + \", \" + #code")
	public Optional<TagDTO> findByCode(String locale, String rootCategory, String code) {
		LOGGER.debug("call TagServiceImpl.findByCode with parameters : {}, {}, {}", locale, rootCategory, code);
		return tagDao.findByCode(locale, rootCategory, code);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key = "#locale + \", \" + #rootCategory + \", \" + #desc")
	public Optional<TagDTO> findByDesc(String locale, String rootCategory, String desc) {
		LOGGER.debug("call TagServiceImpl.findByDesc with parameters : {}, {}", locale, rootCategory, desc);
		return tagDao.findByDesc(locale, rootCategory, desc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #rootCategory + \", \" + #codes.getCacheKey()")
	public List<TagDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}, {}", locale, StringUtils.join(codes.getCodes()));
		return tagDao.findAll(locale, rootCategory, codes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #rootCategory + \", \" + #codes.getCacheKey()")
	public List<TagDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper codes) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}, {}, {}", locale, currency, codes.getCodes());
		return tagDao.findAll(locale, rootCategory, codes);
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
			  @CacheEvict(cacheNames = CACHE_NAME, key="#t.tagId.toString()"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#t.tagCode"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#t.locale + \", \" + #t.tagId.toString()"),
			  @CacheEvict(cacheNames = CACHE_NAME, key="#t.locale + \", \" + #t.tagCode"),
			  @CacheEvict(cacheNames = CACHE_NAME + "Other", allEntries = true)
			})
	public void save(TagEntity t) {
		tagRepository.save(t);
	}

	@Override
	public void update(TagEntity t) {
		tagRepository.save(t);
	}

	@Override
	public void delete(TagEntity t) {
		tagRepository.delete(t);
		
	}

	@Override
	public Optional<TagDTO> findByCode(String locale, String code) {
		return this.findByCode(locale, Constants.defaultProductRootCategoryCode, code);
	}

	@Override
	public Optional<TagDTO> findByDesc(String locale, String desc) {
		return this.findByDesc(locale, Constants.defaultProductRootCategoryCode, desc);
	}

	@Override
	public List<TagDTO> findAll(String locale, StringCollectionWrapper codes) {
		return this.findAll(locale, Constants.defaultProductRootCategoryCode, codes);
	}

	@Override
	public List<TagDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TagDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
