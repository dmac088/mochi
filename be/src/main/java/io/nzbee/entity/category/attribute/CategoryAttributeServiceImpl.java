package io.nzbee.entity.category.attribute;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.Globals;

@Service
public class CategoryAttributeServiceImpl implements ICategoryAttributeService {

	@Autowired
	private CategoryAttributeRepository CategoryAttributeRepository; 
	
	@Autowired
	private Globals globalVars;
	
	@Override
	public Optional<CategoryAttribute> findById(String locale, String currency, long id) {
		return CategoryAttributeRepository.findById(id);
	}
	
	@Override
	public List<CategoryAttribute> findAll(String locale, String currency) {
		return null;//CategoryAttributeRepository.findAll();
	}
	

	@Override
	public Optional<CategoryAttribute> findByCode(String locale, String currency, String code) {
		return CategoryAttributeRepository.findByLclCdAndCategoryCategoryCode(locale, code);
	}

	@Override
	public Optional<CategoryAttribute> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CategoryAttribute> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(CategoryAttribute t) {
		CategoryAttributeRepository.save(t);
	}

	@Override
	public void update(CategoryAttribute t, String[] params) {
		CategoryAttributeRepository.save(t);
	}

	@Override
	public void delete(CategoryAttribute t) {
		CategoryAttributeRepository.delete(t);		
	}

	@Override
	public Optional<CategoryAttribute> getCategoryAttribute(Long id, String locale) {
		return CategoryAttributeRepository.findByLclCdAndCategoryCategoryId(locale, id);
	}
	
	@Override
	public Optional<CategoryAttribute> getCategoryAttributeEN(Long id) {
		return CategoryAttributeRepository.findByLclCdAndCategoryCategoryId(globalVars.getLocaleENGB(), id);
	}
	
	@Override
	public Optional<CategoryAttribute> getCategoryAttributeHK(Long id) {
		return CategoryAttributeRepository.findByLclCdAndCategoryCategoryId(globalVars.getLocaleZHHK(), id);
	}

	@Override
	public CategoryAttribute objectToEntity(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryAttribute objectToEntity(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
