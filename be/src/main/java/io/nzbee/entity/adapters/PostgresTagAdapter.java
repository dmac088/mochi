package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.entity.category.ICategoryService;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tag findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tag> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tag> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return tagService.findAll(locale, currency, codes)
				.stream().map(t -> this.entityToDo(t)).collect(Collectors.toSet());
	}
	
	private Tag entityToDo(io.nzbee.entity.tag.Tag e) {
		return new Tag(
				e.getTagToken(),
				e.getAttributes().stream().filter(t -> t.getLclCd().equals("en-GB")).findFirst().get().getTagDesc(),
				"en-GB",
				"HKD"
				);
	}


}
