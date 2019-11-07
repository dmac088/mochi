package io.nzbee.domain.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import io.nzbee.domain.IDomainObject;
//The Domain object service should be simple and dumb
//it received primitive types and responds with domain objects,
//that are constructed from relevant entity objects
//its simplicity helps us with unit testing

@Service(value = "tagDomainService")
@Transactional
@CacheConfig(cacheNames="tags")
public class TagServiceImpl implements ITagService/*, IFacetService*/ {

	@Autowired
	io.nzbee.dto.category.ICategoryService categoryService;
	
	@Autowired
	io.nzbee.dto.tag.ITagService productTagService;
	
	@Autowired
	io.nzbee.dto.product.IProductService productService;
	

	@Override
	public Optional<Tag> findById(String locale, String currency, Long Id) {
		// TODO Auto-generated method stub
		io.nzbee.dto.tag.Tag pt = productTagService.findById(locale, currency, Id).get();
		return Optional.ofNullable(this.dtoToDO(pt));
	}

	@Override
	public Optional<Tag> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub	
		io.nzbee.dto.tag.Tag  pt = productTagService.findByCode(locale, currency, code).get();
		return Optional.ofNullable(this.dtoToDO(pt));
	}

	@Override
	public Optional<Tag> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		io.nzbee.dto.tag.Tag  pt = productTagService.findByDesc(locale, currency, desc).get();
		return Optional.ofNullable(this.dtoToDO(pt));
	}

	@Override
	public Set<Tag> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Tag> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tag> findAll(String locale, String currency, String categoryDesc, List<IDomainObject> lDo) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public String tokenToCode(String token) {
		// TODO Auto-generated method stub
		return token;
	}

	@Override
	public Tag dtoToDO(io.nzbee.dto.tag.Tag tagDTO) {
		// TODO Auto-generated method stub
		Tag t = new Tag();
		t.setTagId(tagDTO.getTagId());
		t.setTagCode(tagDTO.getTagCode());
		t.setLocale(tagDTO.getLocale());
		t.setTagDesc(tagDTO.getTagDesc());
		return t;
	}

//	@Override
//	public String getFacetField() {
//		// TODO Auto-generated method stub
//		return "tagAFacet";
//	}

//	@Override
//	public String getFacetCategory() {
//		// TODO Auto-generated method stub
//		return "TagFR";
//	}
}
