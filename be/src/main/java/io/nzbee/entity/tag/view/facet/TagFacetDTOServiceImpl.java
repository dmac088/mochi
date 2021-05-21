package io.nzbee.entity.tag.view.facet;

import java.util.List;
import org.apache.tomcat.util.buf.StringUtils;
import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.IFacetService;

@Service(value = "tagFacetService")
public class TagFacetDTOServiceImpl implements ITagFacetDTOService, IFacetService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public static final String CACHE_NAME = "tagCache";
	
	@Autowired
	private ITagFacetDao tagDao;
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #rootCategory + \", \" + #codes.getCacheKey()")
	public List<TagFacetDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}, {}", locale, StringUtils.join(codes.getCodes()));
		return tagDao.findAll(locale, rootCategory, codes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #rootCategory + \", \" + #codes.getCacheKey()")
	public List<TagFacetDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper codes) {
		LOGGER.debug("call TagServiceImpl.findAll with parameters : {}, {}, {}", locale, currency, codes.getCodes());
		return tagDao.findAll(locale, rootCategory, codes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString())")
	public List<TagFacetDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, Double maxPrice) {
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
	public void save(TagFacetDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TagFacetDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TagFacetDTO t) {
		// TODO Auto-generated method stub
		
	}

}
