package io.nzbee.domain.category;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.search.dto.facet.IFacet;

public class CategoryServiceImpl implements ICategoryService {
    
    @Autowired
    private ICategoryPortService categoryService;
    
    @Override
    @Transactional(readOnly=true)
	public Set<Category> findAll(String locale, String currency) {
    	return categoryService.findAll(locale, currency);
	}
    
    @Override
	public Set<Category> findAll(String locale, String currency, String categoryCode, Set<IFacet> selectedFacets) {
		return categoryService.findAll(locale, currency, categoryCode, selectedFacets);
	}
    
    @Override
	public Double getMaxPrice(String locale, String currency, String categoryCode, Set<IFacet> selectedFacets) {
		return new Double(0);
	}

	@Override
	@Transactional(readOnly=true)
	public Category findByCode(String locale, String currency, String code) {
		return categoryService.findByCode(locale, currency, code);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Category findByDesc(String locale, String currency, String desc) {
		return categoryService.findByDesc(locale, currency, desc);
	}
    
    @Override
    @Transactional(readOnly=true)
 	public Set<Category> findByParent(String locale, String currency, String parentCategoryCode) {
    	return categoryService.findByParent(parentCategoryCode, currency, locale);
 	}
    
    @Override
    @Transactional(readOnly=true)
  	public Set<Category> findAllForLevel(String locale, String currency, Long level) {
     	return categoryService.findAllForLevel(locale, currency, level);
  	}	
    
    @Override
    @Transactional(readOnly=true)
  	public Set<ProductCategory> findAllByProductCode(String locale, String currency, String code) {
     	return categoryService.findAllByProductCode(locale, currency, code);
  	}	
    
    @Override
	@Transactional(readOnly=true)
	public Set<Category> findAll(String locale, String currency, Set<String> codes) {
		return categoryService.findAll(locale, currency, codes);
	}
    
    @Override
	public Set<ProductCategory> findAllProductCategories(String locale, String currency) {
		return categoryService.findAllProductCategories(locale, currency);
	}

	@Override
	public Set<BrandCategory> findAllBrandCategories(String locale, String currency) {
		return categoryService.findAllBrandCategories(locale, currency);
	}	

	@Override
	public void save(Category object) {
		categoryService.save(object);
	}

	@Override
	public void delete(Category object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Category object) {
		// TODO Auto-generated method stub
		
	}

}