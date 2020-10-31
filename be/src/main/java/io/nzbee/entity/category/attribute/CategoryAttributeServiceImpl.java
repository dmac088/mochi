package io.nzbee.entity.category.attribute;

import java.util.Optional;
import java.util.Set;
import javax.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;

@Service
public class CategoryAttributeServiceImpl implements ICategoryAttributeService {

	@Autowired
	private CategoryAttributeRepository CategoryAttributeRepository; 
	
	@Override
	public Optional<CategoryAttributeDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<CategoryAttributeDTO> findAll(String locale) {
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
	public Set<CategoryAttributeDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(CategoryAttributeEntity t) {
		CategoryAttributeRepository.save(t);
	}

	@Override
	public void update(CategoryAttributeEntity t, String[] params) {
		CategoryAttributeRepository.save(t);
	}

	@Override
	public void delete(CategoryAttributeEntity t) {
		CategoryAttributeRepository.delete(t);		
	}

	@Override
	public Optional<CategoryAttributeEntity> getCategoryAttribute(Long id, String locale) {
		return CategoryAttributeRepository.findByLclCdAndCategoryCategoryId(locale, id);
	}
	
	@Override
	public Optional<CategoryAttributeEntity> getCategoryAttributeEN(Long id) {
		return CategoryAttributeRepository.findByLclCdAndCategoryCategoryId(Constants.localeENGB, id);
	}
	
	@Override
	public Optional<CategoryAttributeEntity> getCategoryAttributeHK(Long id) {
		return CategoryAttributeRepository.findByLclCdAndCategoryCategoryId(Constants.localeZHHK, id);
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

	
	
//	@Override
//	public CategoryAttributeEntity objectToEntity(Object[] o, String locale, String currency) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public CategoryAttributeEntity objectToEntity(Tuple t, String locale, String currency) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public CategoryAttributeEntity objectToEntity(Object[] o, String locale) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public CategoryAttributeEntity objectToEntity(Tuple t, String locale) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
