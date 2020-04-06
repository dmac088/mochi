package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProduct;

@Component
public class PostgresCategoryAdapter implements ICategoryPortService {

	
	@Autowired 
	private ICategoryService categoryService;
	

	@Override
	public Set<Category> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
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
				.stream().map(b -> this.entityToDo(b)).collect(Collectors.toSet());
	}

	@Override
	public Category findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return entityToDo(categoryService.findByCode(locale, currency, code).get());
				
	}

	
	private Category entityToDo(io.nzbee.entity.category.Category e) {
		if(e instanceof CategoryProduct) {
			return new ProductCategory(
					e.getCategoryCode(),
					e.getAttributes().stream().filter(c -> c.getLclCd().equals(e.getLocale())).findFirst().get().getCategoryDesc(),
					true,
					e.getCategoryLevel(),
					e.getCategoryType().getDesc(),
					e.getObjectCount(),
					e.getParent().get().getCategoryCode(),
					e.getLocale(), 
					e.getCurrency()
			);
		}
		return new BrandCategory(
				e.getCategoryCode(),
				e.getAttributes().stream().filter(c -> c.getLclCd().equals(e.getLocale())).findFirst().get().getCategoryDesc(),
				true,
				e.getCategoryLevel(),
				e.getCategoryType().getDesc(),
				e.getObjectCount(),
				e.getParent().get().getCategoryCode(),
				e.getLocale(), 
				e.getCurrency()
		);
	}

	

}
