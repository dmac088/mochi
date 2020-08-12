package io.nzbee.domain.tag;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

public class TagServiceImpl implements ITagService {

	@Autowired
	private ITagService tagService;
	
	@Override
	public Tag findByCode(String locale, String code) {
		return tagService.findByCode(locale, code);
	}

	@Override
	public Tag findByDesc(String locale, String desc) {
		return tagService.findByDesc(locale, desc);
	}

	@Override
	public Set<Tag> findAll(String locale) {
		return tagService.findAll(locale);
	}

	@Override
	public Set<Tag> findAll(String locale, Set<String> codes) {
		return tagService.findAll(locale, codes);
	}

	@Override
	public void save(Tag object) {
		tagService.save(object);
	}

	@Override
	public void delete(Tag object) {
		tagService.delete(object);
	}

	@Override
	public void update(Tag object) {
		tagService.update(object);
	}
}
