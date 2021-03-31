package io.nzbee.entity.adapters;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.tag.ITagMapper;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagDTO;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.entity.tag.attribute.TagAttributeEntity;
import io.nzbee.exceptions.CustomException;

@Component
public class PostgresTagAdapter  implements ITagPortService {

	@Autowired 
	private ITagService tagService;
	
	@Autowired
	private ITagMapper tagMapper;
	
	@Override
	@Transactional(readOnly = true)
	public Tag findByCode(String locale, String code) {
		TagDTO t = tagService.findByCode(locale, code)
				.orElseThrow(() -> new CustomException("Tag with code " + code + " not found!"));
		return tagMapper.DTOToDo(t);
	}

	@Override
	@Transactional(readOnly = true)
	public Tag findByDesc(String locale, String desc) {
		TagDTO t = tagService.findByDesc(locale, desc)
				.orElseThrow(() -> new CustomException("Tag with desc " + desc + " not found!"));
		return tagMapper.DTOToDo(t);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Tag> findAll(String locale) {
		return tagService.findAll(locale)
				.stream().map(t -> tagMapper.DTOToDo(t)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tag> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Double maxPrice) {
		return tagService.findAll(locale, currency, categoryCode, new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(brandCodes), maxPrice)
				.stream().map(b -> (Tag) tagMapper.DTOToDo(b)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tag> findAll(String locale, Set<String> codes) {
		return tagService.findAll(locale, new StringCollectionWrapper(codes))
				.stream().map(t -> tagMapper.DTOToDo(t)).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void save(Tag domainObject) {
		
		Optional<TagEntity> ot = 
				tagService.findByCode(domainObject.getTagCode().toUpperCase());
		
		
		TagEntity t = 
				(ot.isPresent())
				? ot.get() 
				: new TagEntity();
				
		TagAttributeEntity ta = new TagAttributeEntity();
		
		if(ot.isPresent()) {
			Optional<TagAttributeEntity> ota =
			ot.get().getAttributes().stream().filter(a -> a.getLclCd().equals(domainObject.getLocale())).findFirst();
			ta = (ota.isPresent()) 
			? ota.get()
			: new TagAttributeEntity();
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
