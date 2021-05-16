package io.nzbee.entity.category.product.view;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

	@Autowired
	private IProductCategoryViewDao productCategoryDao;
	
	@Override
	public List<ProductCategoryViewDTO> findAll(String locale, String rootCategory) {
		return productCategoryDao.findAll(locale, rootCategory);
	}
	
	@Override
	public void save(ProductCategoryViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProductCategoryViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProductCategoryViewDTO t) {
		// TODO Auto-generated method stub
		
	}

}
