package io.nzbee.entity.ports;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.domain.adapters.ITagPortService;
import io.nzbee.domain.tag.Tag;

public class PostgresTagPortService  implements ITagPortService {

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
	public Set<Tag> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tag> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

}
