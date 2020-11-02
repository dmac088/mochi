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
	private ITagAttributeRepository tagAttributeRepository; 
	
	@Override
	public Optional<TagAttributeEntity> findById(long id) {
		return tagAttributeRepository.findById(id);
	}

	@Override
	public Optional<TagAttributeEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TagAttributeEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TagAttributeEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<TagAttributeDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
		//return TagAttributeRepository.findById(id);
	}
	
	@Override
	public Set<TagAttributeDTO> findAll(String locale) {
		return null;//TagAttributeRepository.findAll();
	}

	@Override
	public Optional<TagAttributeDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
		//return TagAttributeRepository.findByLclCdAndTagTagCode(locale, code);
	}

	@Override
	public Optional<TagAttributeDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<TagAttributeDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(TagAttributeEntity t) {
		tagAttributeRepository.save(t);
	}

	@Override
	public void update(TagAttributeEntity t, String[] params) {
		tagAttributeRepository.save(t);
	}

	@Override
	public void delete(TagAttributeEntity t) {
		tagAttributeRepository.delete(t);		
	}

	@Override
	public Optional<TagAttributeEntity> getTagAttribute(Long id, String locale) {
		return tagAttributeRepository.findByLclCdAndTagTagId(locale, id);
	}
	
	@Override
	public Optional<TagAttributeEntity> getTagAttributeEN(Long id) {
		return tagAttributeRepository.findByLclCdAndTagTagId(Constants.localeENGB, id);
	}
	
	@Override
	public Optional<TagAttributeEntity> getTagAttributeHK(Long id) {
		return tagAttributeRepository.findByLclCdAndTagTagId(Constants.localeZHHK, id);
	}

	@Override
	public TagAttributeDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagAttributeDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagAttributeDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagAttributeDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
