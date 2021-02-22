package io.nzbee.entity.category.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;

@Service
public class CategoryBrandServiceImpl implements ICategoryBrandService {

	@Autowired
	private ICategoryBrandDao brandCategoryDao;
	
	@Override
	public List<CategoryBrandDTO> findAllByBrandCode(String locale, String currency, String brandCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBrandDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBrandDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrandDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrandDTO> findByCode(String locale, String code) {
		return brandCategoryDao.findByCode(locale, code);
	}
	
	@Override
	public Optional<CategoryBrandDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CategoryBrandEntity t) {
		brandCategoryDao.save(t);
	}

	@Override
	public void update(CategoryBrandEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryBrandEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryBrandEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrandEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrandEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBrandEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBrandDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

}
