package io.nzbee.entity.tag.attribute;

import java.util.Optional;
import java.util.Set;
import javax.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;

@Service
public class TagAttributeServiceImpl implements ITagAttributeService {

	@Autowired
	private TagAttributeRepository TagAttributeRepository; 
	
	@Override
	public Optional<TagAttribute> findById(String locale, Long id) {
		return TagAttributeRepository.findById(id);
	}
	
	@Override
	public Set<TagAttribute> findAll(String locale) {
		return null;//TagAttributeRepository.findAll();
	}
	

	@Override
	public Optional<TagAttribute> findByCode(String locale, String code) {
		return TagAttributeRepository.findByLclCdAndTagTagCode(locale, code);
	}

	@Override
	public Optional<TagAttribute> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<TagAttribute> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(TagAttribute t) {
		TagAttributeRepository.save(t);
	}

	@Override
	public void update(TagAttribute t, String[] params) {
		TagAttributeRepository.save(t);
	}

	@Override
	public void delete(TagAttribute t) {
		TagAttributeRepository.delete(t);		
	}

	@Override
	public Optional<TagAttribute> getTagAttribute(Long id, String locale) {
		return TagAttributeRepository.findByLclCdAndTagTagId(locale, id);
	}
	
	@Override
	public Optional<TagAttribute> getTagAttributeEN(Long id) {
		return TagAttributeRepository.findByLclCdAndTagTagId(Constants.localeENGB, id);
	}
	
	@Override
	public Optional<TagAttribute> getTagAttributeHK(Long id) {
		return TagAttributeRepository.findByLclCdAndTagTagId(Constants.localeZHHK, id);
	}

	@Override
	public TagAttribute objectToEntity(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagAttribute objectToEntity(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagAttribute objectToEntity(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagAttribute objectToEntity(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
