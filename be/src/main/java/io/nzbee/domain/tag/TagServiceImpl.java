package io.nzbee.domain.tag;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

public class TagServiceImpl implements ITagService {

	@Autowired
	private ITagService tagService;
	
	@Override
	public Tag findByCode(String locale, String currency, String code) {
		return tagService.findByCode(locale, currency, code);
	}

	@Override
	public Tag findByDesc(String locale, String currency, String desc) {
		return tagService.findByDesc(locale, currency, desc);
	}

	@Override
	public Set<Tag> findAll(String locale, String currency) {
		return tagService.findAll(locale, currency);
	}

	@Override
	public Set<Tag> findAll(String locale, String currency, Set<String> codes) {
		return tagService.findAll(locale, currency, codes);
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
