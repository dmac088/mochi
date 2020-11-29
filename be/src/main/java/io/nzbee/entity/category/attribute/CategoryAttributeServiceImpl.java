package io.nzbee.entity.category.attribute;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;

@Service
public class CategoryAttributeServiceImpl implements ICategoryAttributeService {

	@Autowired
	private CategoryAttributeRepository categoryAttributeRepository; 
	
	@Override
	public Optional<CategoryAttributeEntity> findById(long id) {
		return categoryAttributeRepository.findById(id);
	}

	@Override
	public Optional<CategoryAttributeEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryAttributeEntity> findAll() {
		return categoryAttributeRepository.findAll();
	}

	
	@Override
	public Optional<CategoryAttributeDTO> findById(String locale, Long id) {
		return null;
	}
	
	@Override
	public List<CategoryAttributeDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryAttributeDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryAttributeDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CategoryAttributeDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(CategoryAttributeEntity t) {
		categoryAttributeRepository.save(t);
	}

	@Override
	public void update(CategoryAttributeEntity t, String[] params) {
		categoryAttributeRepository.save(t);
	}

	@Override
	public void delete(CategoryAttributeEntity t) {
		categoryAttributeRepository.delete(t);		
	}

	@Override
	public Optional<CategoryAttributeEntity> getCategoryAttribute(Long id, String locale) {
		return categoryAttributeRepository.findByLclCdAndCategoryCategoryId(locale, id);
	}
	
	@Override
	public Optional<CategoryAttributeEntity> getCategoryAttributeEN(Long id) {
		return categoryAttributeRepository.findByLclCdAndCategoryCategoryId(Constants.localeENGB, id);
	}
	
	@Override
	public Optional<CategoryAttributeEntity> getCategoryAttributeHK(Long id) {
		return categoryAttributeRepository.findByLclCdAndCategoryCategoryId(Constants.localeZHHK, id);
	}

	@Override
	public CategoryAttributeDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryAttributeDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryAttributeDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryAttributeDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryAttributeEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
