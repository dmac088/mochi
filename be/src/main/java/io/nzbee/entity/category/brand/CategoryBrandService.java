package io.nzbee.entity.category.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryBrandService implements ICategoryBrandService {

	@Autowired
	private ICategoryBrandDao brandCategoryDao;
	
	@Override
	public List<CategoryBrand> findAllByBrandCode(String locale, String currency, String brandCode) {
		return brandCategoryDao.findAllByBrandCode(locale, currency, brandCode);
	}

	@Override
	public List<CategoryBrand> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBrand> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrand> findById(String locale, String currency, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrand> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrand> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CategoryBrand t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CategoryBrand t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryBrand t) {
		// TODO Auto-generated method stub
		
	}

}
