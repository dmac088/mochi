package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.entity.IMapper;
import io.nzbee.entity.category.ICategoryService;

@Component
public class PostgresCategoryAdapter implements ICategoryPortService {

	
	@Autowired 
	private ICategoryService categoryService;
	
	@Autowired
	@Qualifier(value = "categoryMapper")
	private IMapper<io.nzbee.domain.category.Category, io.nzbee.entity.category.Category> categoryMapper;

	@Override
	public Set<Category> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return categoryService.findAll(locale, currency, codes)
				.stream().map(c -> categoryMapper.entityToDo(c)).collect(Collectors.toSet());
	}
	
	@Override
	public Optional<Category> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Category> findAllForLevel(String locale, String currency, Long level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Category> findByParent(String parentCategoryCode, String currency, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Category domainObject) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public Set<Category> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return categoryService.findAll(locale, currency)
				.stream().map(c -> categoryMapper.entityToDo(c)).collect(Collectors.toSet());
	}

	@Override
	public Category findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return categoryMapper.entityToDo(categoryService.findByCode(locale, currency, code).get());
				
	}	

}
