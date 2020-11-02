package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.entity.tag.ITagMapper;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagDTO;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.exceptions.tag.TagNotFoundException;

@Component
public class PostgresTagAdapter  implements ITagPortService {

	@Autowired 
	private ITagService tagService;
	
	@Autowired
	private ITagMapper tagMapper;
	
	@Override
	@Cacheable("tags")
	@Transactional(readOnly = true)
	public Tag findByCode(String locale, String code) {
		TagDTO t = tagService.findByCode(locale, code)
				.orElseThrow(() -> new TagNotFoundException("Tag with code " + code + " not found!"));
		return tagMapper.DTOToDo(t);
	}

	@Override
	@Cacheable("tags")
	@Transactional(readOnly = true)
	public Tag findByDesc(String locale, String desc) {
		TagDTO t = tagService.findByDesc(locale, desc)
				.orElseThrow(() -> new TagNotFoundException("Tag with desc " + desc + " not found!"));
		return tagMapper.DTOToDo(t);
	}
	
	@Override
	@Cacheable("tags")
	@Transactional(readOnly = true)
	public Set<Tag> findAll(String locale) {
		return tagService.findAll(locale)
				.stream().map(t -> tagMapper.DTOToDo(t)).collect(Collectors.toSet());
	}

	@Override
	@Cacheable("tags")
	@Transactional(readOnly = true)
	public Set<Tag> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Double maxPrice) {
		return tagService.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, maxPrice)
				.stream().map(b -> (Tag) tagMapper.DTOToDo(b)).collect(Collectors.toSet());
	}

	@Override
	@Cacheable("tags")
	@Transactional(readOnly = true)
	public Set<Tag> findAll(String locale, Set<String> codes) {
		return tagService.findAll(locale, codes)
				.stream().map(t -> tagMapper.DTOToDo(t)).collect(Collectors.toSet());
	}
	
	@Override
	@Transactional
	public void save(Tag domainObject) {
		
		Optional<io.nzbee.entity.tag.TagEntity> ot = 
				tagService.findByCode(domainObject.getTagCode().toUpperCase());
		
		
		io.nzbee.entity.tag.TagEntity t = 
				(ot.isPresent())
				? ot.get() 
				: new io.nzbee.entity.tag.TagEntity();
				
		io.nzbee.entity.tag.attribute.TagAttributeEntity ta = new io.nzbee.entity.tag.attribute.TagAttributeEntity();
		
		if(ot.isPresent()) {
			Optional<io.nzbee.entity.tag.attribute.TagAttributeEntity> ota =
			ot.get().getAttributes().stream().filter(a -> a.getLclCd().equals(domainObject.getLocale())).findFirst();
			ta = (ota.isPresent()) 
			? ota.get()
			: new io.nzbee.entity.tag.attribute.TagAttributeEntity();
		}		
		
		ta.setTagDesc(domainObject.getTagDesc());
		ta.setLclCd(domainObject.getLocale());
		ta.setTag(t);
		
		t.setTagCode(domainObject.getTagCode().toUpperCase());
		t.setLocale(domainObject.getLocale());
		t.addTagAttribute(ta);
		
		
		tagService.save(t);
	}
	

	@Override
	public void update(Tag domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tag domainObject) {
		// TODO Auto-generated method stub
		
	}


}
