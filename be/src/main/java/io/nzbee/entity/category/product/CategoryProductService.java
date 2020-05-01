package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryProductService implements ICategoryProductService {

	@Autowired
	private ICategoryProductDao productCategoryDao;
	
	@Override
	public List<CategoryProduct> findAllByProductCode(String locale, String currency, String productCode) {
		return productCategoryDao.findAllByProductCode(locale, currency, productCode);
	}
	
	@Override
	public Optional<CategoryProduct> findPrimaryByProductCode(String locale, String currency, String productCode) {
		return productCategoryDao.findPrimaryByProductCode(locale, currency, productCode);
	}

	@Override
	public List<CategoryProduct> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryProduct> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProduct> findById(String locale, String currency, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProduct> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProduct> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CategoryProduct t) {
		productCategoryDao.save(t);
	}

	@Override
	public void update(CategoryProduct t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryProduct t) {
		// TODO Auto-generated method stub
		
	}

}
