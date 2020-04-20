package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.entity.tag.ITagService;

@Component
public class PostgresTagAdapter  implements ITagPortService {

	@Autowired 
	private ITagService tagService;
	
	@Override
	public Optional<Tag> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Tag tag) {
		
		io.nzbee.entity.tag.Tag t = new io.nzbee.entity.tag.Tag();
		t.setCode(tag.getTagCode());
		t.setLocale(tag.getLocale());
		t.setCurrency(tag.getCurrency());
		
		io.nzbee.entity.tag.attribute.TagAttribute ta = new io.nzbee.entity.tag.attribute.TagAttribute();
		ta.setTagDesc(tag.getTagDesc());
		ta.setLclCd(tag.getLocale());
		t.addTagAttribute(ta);
		ta.setTag(t);
		t.addTagAttribute(ta);
		
		tagService.save(t);
	}

	@Override
	public Tag findByCode(String locale, String currency, String code) {
		return this.entityToDo(tagService.findByCode(locale, currency, code).get());
	}

	@Override
	public Tag findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return this.entityToDo(tagService.findByDesc(locale, currency, desc).get());
	}

	@Override
	public Set<Tag> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tag> findAll(String locale, String currency, Set<String> codes) {
		return tagService.findAll(locale, currency, codes)
				.stream().map(t -> this.entityToDo(t)).collect(Collectors.toSet());
	}
	
	private Tag entityToDo(io.nzbee.entity.tag.Tag e) {
		return new Tag(
				e.getCode(),
				e.getAttributes().stream().filter(t -> t.getLclCd().equals(e.getLocale())).findFirst().get().getTagDesc(),
				"en-GB",
				"HKD"
				);
		
	}


}
